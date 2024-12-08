package com.yoong.sunnyside.domain.auth.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.auth.dto.*
import com.yoong.sunnyside.domain.auth.service.AuthService
import com.yoong.sunnyside.infra.security.MemberPrincipal
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.security.Principal

@Tag(name = "중복 검사 및 인증 절차 API", description = "닉네임 중복 검사 및 이메일 인증 절차를 위한 API 입니다")
@RestController
@RequestMapping()
class AuthController(
    private val authService: AuthService
){

    @GetMapping("/check/nickname")
    @Operation(summary = "닉네임 중복 검사 API")
    fun checkNickname(
        @RequestParam nickname: String,
    ): ResponseEntity<NicknameResponse>
        = ResponseEntity.status(HttpStatus.OK).body(authService.checkNickname(nickname))

    @PostMapping("/email/send")
    @Operation(summary = "이메일 인증 요청 API")
    fun sendEmail(
        @RequestBody emailRequest: EmailRequest
    ): ResponseEntity<DefaultResponse>
            = ResponseEntity.status(HttpStatus.OK).body(authService.sendEmail(emailRequest))

    @PostMapping("/email/confirm")
    @Operation(summary = "이메일 인증 코드 검사 API")
    fun verifyEmail(
        @RequestBody verifyCodeRequest: VerifyCodeRequest,
    ): ResponseEntity<DefaultResponse>
            = ResponseEntity.status(HttpStatus.OK).body(authService.verifyEmail(verifyCodeRequest))

    @GetMapping("/role")
    @PreAuthorize("hasRole('ADMIN') or hasRole('BUSINESS') or hasRole('CONSUMER')")
    @Operation(summary = "토큰 권한 확인 API")
    fun getRole(
        @AuthenticationPrincipal principal: MemberPrincipal,
    ): ResponseEntity<MemberRoleResponse>
        = ResponseEntity.status(HttpStatus.OK).body(authService.getRole(principal))

}