package com.yoong.sunnyside.domain.auth.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "중복 검사 및 인증 절차 API", description = "닉네임 중복 검사 및 이메일 인증 절차를 위한 API 입니다")
@RestController
@RequestMapping()
class AuthController {

    @GetMapping("/check/nickname")
    fun checkNickname(): ResponseEntity<DefaultResponseDto>
        = ResponseEntity.status(HttpStatus.OK).build()

    @PostMapping("/email/send")
    fun sendEmail(): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).build()

    @PostMapping("/email/confirm")
    fun verifyEmail(): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).build()

}