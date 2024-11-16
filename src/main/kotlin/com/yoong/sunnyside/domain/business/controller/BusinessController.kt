package com.yoong.sunnyside.domain.business.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.business.dto.*
import com.yoong.sunnyside.domain.business.service.BusinessService
import com.yoong.sunnyside.infra.security.MemberPrincipal
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/business")
@Tag(name = "사업자(제공자)", description = "사업자(제공자) 관련 CRUD")
class BusinessController(private val businessService: BusinessService) {

    @Operation(summary = "사업자 신청")
    @PostMapping("/apply")
    fun signup(@RequestBody request: BusinessSignupRequest): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(businessService.signUp(request))
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(businessService.login(request))
    }

    @Operation(summary = "비밀번호 변경")
    @PatchMapping("/passwd")
    fun passwd(
        @RequestBody request: PasswordChangeRequest,
        @AuthenticationPrincipal auth: MemberPrincipal
    ): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.OK).body(businessService.passwd(request, auth.id))
    }

    @Operation(summary = "회원 정보 수정")
    @PutMapping("/member")
    fun memberModify(
        @RequestBody request: BusinessModifyRequest,
        @AuthenticationPrincipal auth: MemberPrincipal
    ): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @Operation(summary = "내정보조회")
    @GetMapping("/member")
    fun myDetail(
        @AuthenticationPrincipal auth: MemberPrincipal
    ): ResponseEntity<BusinessResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("/member")
    fun memberDelete(
        @AuthenticationPrincipal auth: MemberPrincipal
    ): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO())
    }

    @Operation(summary = "사업자 등록 번호 검사")
    @PostMapping("/businessNumber")
    fun numberCheck(request: BusinessNumberRequest): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @Operation(summary = "사업자 등록증 이미지 등록(업로드 한 링크 저장)")
    @PostMapping("/businessImage")
    fun businessImage(request: BusinessImageRequest): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }


}