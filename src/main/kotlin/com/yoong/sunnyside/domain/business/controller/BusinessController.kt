package com.yoong.sunnyside.domain.business.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.business.dto.*
import com.yoong.sunnyside.domain.business.service.BusinessService
import com.yoong.sunnyside.infra.security.MemberPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/business")
class BusinessController(private val businessService: BusinessService) {

    @PostMapping("/apply")
    fun signup(@RequestBody request: BusinessSignupRequest): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(businessService.signUp(request))
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(businessService.login(request))
    }

    @PatchMapping("/passwd")
    fun passwd(
        @RequestBody request: PasswordChangeRequest,
        @AuthenticationPrincipal auth: MemberPrincipal
    ): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(businessService.passwd(request, auth.id))
    }

    @PutMapping("/member")
    fun memberModify(
        @RequestBody request: BusinessModifyRequest,
        @AuthenticationPrincipal auth: MemberPrincipal
    ): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @GetMapping("/member")
    fun myDetail(
        @AuthenticationPrincipal auth: MemberPrincipal
    ): ResponseEntity<BusinessResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @DeleteMapping("/member")
    fun memberDelete(
        @AuthenticationPrincipal auth: MemberPrincipal
    ): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO())
    }

    @PostMapping("/businessNumber")
    fun numberCheck(request: BusinessNumberRequest): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @PostMapping("/businessImage")
    fun businessImage(request: BusinessImageRequest): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }


}