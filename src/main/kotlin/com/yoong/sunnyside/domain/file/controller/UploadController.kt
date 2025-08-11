package com.yoong.sunnyside.domain.file.controller

import com.yoong.sunnyside.domain.file.service.ImageUploadService
import com.yoong.sunnyside.infra.security.MemberPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/upload")
class UploadController(
    private val fileUploadService: ImageUploadService
) {

    @GetMapping("/presigned")
    fun presignedUrl(
        @AuthenticationPrincipal principal: MemberPrincipal,
        @RequestParam fileName: String,
        @RequestParam domain: String,
    ): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CREATED).body(fileUploadService.presignedUrl(domain, fileName))
    }

    @DeleteMapping("/images")
    fun deleteImage(
        @AuthenticationPrincipal principal: MemberPrincipal,
        @RequestParam domain: String,
        @RequestParam fileName: String,
    ): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.OK).body(fileUploadService.delete(domain, fileName))
    }
}