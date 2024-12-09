package com.yoong.sunnyside.domain.consumer.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.business.dto.LoginResponse
import com.yoong.sunnyside.domain.consumer.dto.ConsumerLoginRequest
import com.yoong.sunnyside.domain.consumer.dto.ConsumerSignupRequest
import com.yoong.sunnyside.domain.consumer.dto.ConsumerUpdateRequest
import com.yoong.sunnyside.domain.consumer.dto.PasswordRequest
import com.yoong.sunnyside.domain.consumer.service.ConsumerService
import com.yoong.sunnyside.infra.security.MemberPrincipal
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "소비자", description = "소비자 로그인 관련 CRUD")
@RestController
@RequestMapping("/consumer")
class ConsumerController(
    private val consumerService: ConsumerService
){

    @Operation(summary = "소비자 회원 가입 API")
    @PostMapping("/signup")
    fun signup(
        @RequestBody consumerSignupRequest: ConsumerSignupRequest
    ): ResponseEntity<DefaultResponse>
    = ResponseEntity.status(HttpStatus.CREATED).body(consumerService.signUp(consumerSignupRequest))

    @Operation(summary = "소비자 로그인 API")
    @PostMapping("/login")
    fun login(
        @RequestBody consumerLoginRequest: ConsumerLoginRequest
    ): ResponseEntity<LoginResponse>
    = ResponseEntity.status(HttpStatus.OK).body(consumerService.login(consumerLoginRequest))

    @Operation(summary = "소비자 비밀번호 변경 API")
    @PreAuthorize("hasRole('CONSUMER')")
    @PatchMapping("/passwd")
    fun changePassword(
        @RequestBody passwordRequest: PasswordRequest,
        @AuthenticationPrincipal principal: MemberPrincipal
    ): ResponseEntity<DefaultResponse>
            = ResponseEntity.status(HttpStatus.OK).body(consumerService.changePassword(passwordRequest, principal.id))

    @Operation(summary = "소비자 회원 정보 변경 API")
    @PreAuthorize("hasRole('CONSUMER')")
    @PutMapping
    fun updateConsumer(
        @RequestBody consumerUpdateRequest: ConsumerUpdateRequest,
        @AuthenticationPrincipal principal: MemberPrincipal
    ): ResponseEntity<DefaultResponse>
            = ResponseEntity.status(HttpStatus.OK).body(consumerService.updateConsumer(consumerUpdateRequest, principal.id))

    @Operation(summary = "소비자 회원 탈퇴 API")
    @PreAuthorize("hasRole('CONSUMER')")
    @DeleteMapping
    fun deleteConsumer(
        @AuthenticationPrincipal principal: MemberPrincipal
    ): ResponseEntity<DefaultResponse>
            = ResponseEntity.status(HttpStatus.OK).body(consumerService.deleteConsumer(principal.id))


}