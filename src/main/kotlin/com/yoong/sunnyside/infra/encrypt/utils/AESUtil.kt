package com.yoong.sunnyside.infra.encrypt.utils

import com.yoong.sunnyside.infra.encrypt.Encrypt
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.spec.AlgorithmParameterSpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

@Component
class AESUtil(
    @Value("\${encrypt.key}") private val key : String,
    @Value("\${encrypt.iv}") private val iv : String,
    @Value("\${encrypt.transformation}") private val transformation: String,
    @Value("\${encrypt.bitKeyType}") private val bitKeyType: Int,
    @Value("\${encrypt.bytesIvLength}") private val bytesIvLength: Int,
): Encrypt {

    private final val log = LoggerFactory.getLogger("AESUtil")

    private fun getSecretKeySpec(bitKeyType: Int): SecretKeySpec {
        val bytesKey = key.toByteArray(Charsets.UTF_8)

        val bitKeySize = bitKeyType / 8

        val keyLength = bytesKey.size.coerceAtMost(bitKeySize)

        val adjustedKey = ByteArray(bitKeySize) { 0 }

        bytesKey.copyInto(adjustedKey, destinationOffset = 0, startIndex = 0, endIndex = keyLength)

        return SecretKeySpec(adjustedKey, "AES")
    }

    private fun getIvSpec(): AlgorithmParameterSpec{

        val bytesIv = iv.toByteArray(Charsets.UTF_8)

        val adjustedIv = ByteArray(16) { 0 }

        val ivLength = bytesIv.size.coerceAtMost(16)

        bytesIv.copyInto(adjustedIv, destinationOffset = 0, startIndex = 0, endIndex = ivLength)

        return IvParameterSpec(adjustedIv)
    }

    override fun encrypt(content: String): String {
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKeySpec(bitKeyType), getIvSpec())

        val encryptedBytes = cipher.doFinal(content.toByteArray(charset("UTF-8")))

        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    override fun decrypt(content: String): String {
        log.info(transformation)
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.DECRYPT_MODE, getSecretKeySpec(bitKeyType), getIvSpec())
        val decodedBytes = cipher.doFinal(Base64.getDecoder().decode(content))
        return String(decodedBytes)
    }
}