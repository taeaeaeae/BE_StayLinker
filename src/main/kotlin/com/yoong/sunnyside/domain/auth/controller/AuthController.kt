package com.yoong.sunnyside.domain.auth.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.auth.dto.AccessTokenRequest
import com.yoong.sunnyside.domain.auth.dto.EmailRequest
import com.yoong.sunnyside.domain.auth.dto.MemberRoleResponse
import com.yoong.sunnyside.domain.auth.dto.NicknameRequest
import com.yoong.sunnyside.domain.auth.service.AuthService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "중복 검사 및 인증 절차 API", description = "닉네임 중복 검사 및 이메일 인증 절차를 위한 API 입니다")
@RestController
@RequestMapping()
class AuthController(
    private val authService: AuthService
){

    @GetMapping("/check/nickname")
    @Operation(summary = "닉네임 중복 검사 API")
    fun checkNickname(
        @RequestBody nickNameRequest: NicknameRequest
    ): ResponseEntity<DefaultResponse>
        = ResponseEntity.status(HttpStatus.OK).body(authService.checkNickname(nickNameRequest))

    @PostMapping("/email/send")
    @Operation(summary = "이메일 인증 요청 API")
    fun sendEmail(
        @RequestBody emailRequest: EmailRequest
    ): ResponseEntity<DefaultResponse>
            = ResponseEntity.status(HttpStatus.OK).body(authService.sendEmail(emailRequest))

    @PostMapping("/email/confirm")
    @Operation(summary = "이메일 인증 코드 검사 API")
    fun verifyEmail(
        @RequestBody emailRequest: EmailRequest,
        @RequestParam code: String
    ): ResponseEntity<EmailRequest>
            = ResponseEntity.status(HttpStatus.OK).body(authService.verifyEmail(emailRequest, code))

    @GetMapping("/role")
    @Operation(summary = "토큰 권한 확인 API")
    fun getRole(
        @RequestBody accessTokenRequest: AccessTokenRequest
    ): ResponseEntity<MemberRoleResponse>
        = ResponseEntity.status(HttpStatus.OK).body(authService.getRole(accessTokenRequest))

}