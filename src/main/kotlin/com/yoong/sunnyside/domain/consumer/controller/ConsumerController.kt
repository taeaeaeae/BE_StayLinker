package com.yoong.sunnyside.domain.consumer.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.business.dto.LoginResponse
import com.yoong.sunnyside.domain.consumer.dto.ConsumerLoginRequestDto
import com.yoong.sunnyside.domain.consumer.dto.ConsumerSignupRequestDto
import com.yoong.sunnyside.domain.consumer.dto.ConsumerUpdateRequestDto
import com.yoong.sunnyside.domain.consumer.dto.PasswordRequestDto
import com.yoong.sunnyside.domain.consumer.service.ConsumerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "소비자", description = "소비자 로그인 관련 CRUD")
@RestController
@RequestMapping("/consumer")
class ConsumerController(
    private val consumerService: ConsumerService
){

    @Operation(summary = "소비자 회원 가입 API")
    @PostMapping("/signup")
    fun signup(
        @RequestBody consumerSignupRequestDto: ConsumerSignupRequestDto
    ): ResponseEntity<DefaultResponseDto>
    = ResponseEntity.status(HttpStatus.CREATED).body(consumerService.signUp(consumerSignupRequestDto))

    @Operation(summary = "소비자 로그인 API")
    @PostMapping("/login")
    fun login(
        @RequestBody consumerLoginRequestDto: ConsumerLoginRequestDto
    ): ResponseEntity<LoginResponse>
    = ResponseEntity.status(HttpStatus.OK).body(consumerService.login(consumerLoginRequestDto))

    @Operation(summary = "소비자 비밀번호 변경 API")
    @PatchMapping("/passwd")
    fun changePassword(
        @RequestBody passwordRequestDto: PasswordRequestDto
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(consumerService.changePassword(passwordRequestDto))

    @Operation(summary = "소비자 회원 정보 변경 API")
    @PutMapping
    fun updateConsumer(
        @RequestBody consumerUpdateRequestDto: ConsumerUpdateRequestDto
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(consumerService.updateConsumer(consumerUpdateRequestDto))

    @Operation(summary = "소비자 회원 탈퇴 API")
    @DeleteMapping
    fun deleteConsumer(): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(consumerService.deleteConsumer())


}