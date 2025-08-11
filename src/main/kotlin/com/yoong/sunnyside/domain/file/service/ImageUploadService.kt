package com.yoong.sunnyside.domain.file.service

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.DeleteObjectRequest
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

@Service
class ImageUploadService(
    private val amazonS3Client: AmazonS3Client
) {
    @Value("\${cloud.aws.s3.bucket}")
    private lateinit var bucket: String

    fun presignedUrl(domain: String, fileName: String): String {
        val expiration = Date(System.currentTimeMillis() + 60 * 30 * 1000)
        val url = GeneratePresignedUrlRequest(bucket, "$domain/$fileName")
            .withMethod(com.amazonaws.HttpMethod.PUT)
            .withExpiration(expiration)
        return amazonS3Client.generatePresignedUrl(url).toString()
    }


    fun delete(domain: String, fileName: String) {
        val toDelete = DeleteObjectRequest(bucket, "$domain/$fileName")
        amazonS3Client.deleteObject(toDelete)
    }
}