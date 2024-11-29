package com.yoong.sunnyside.domain.admin.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.admin.dto.CompanionRequest
import com.yoong.sunnyside.domain.admin.service.AdminService
import com.yoong.sunnyside.domain.business.dto.BusinessResponse
import com.yoong.sunnyside.domain.business.dto.TempBusinessResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "관리자", description = "관리자 전용 API")
@RestController
@RequestMapping("/admin")
class AdminController(
    private val adminService: AdminService
) {
    @Operation(summary = "사업자 심사 통과", description = "auditId에 임시사업자 아이디")
    @PatchMapping("/regist/{auditId}")
    fun allow(@PathVariable("auditId") id: Long): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.allowBusiness(id))
    }

    @Operation(summary = "사업자 심사 반려", description = "auditId에 임시사업자 아이디")
    @PatchMapping("/companion/{auditId}")
    fun companion(@PathVariable("auditId") id: Long, request: CompanionRequest): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @Operation(summary = "사업자 심사 제거", description = "auditId에 임시사업자 아이디")
    @DeleteMapping("/remove/{auditId}")
    fun deleteAudit(@PathVariable("auditId") id: Long): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO())
    }

    @Operation(summary = "사업자 심사 단건 조회", description = "auditId에 임시사업자 아이디")
    @GetMapping("/audit/{auditId}")
    fun audit(@PathVariable("auditId") aid: Long): ResponseEntity<TempBusinessResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @Operation(summary = "사업자 심사 목록 조회")
    @GetMapping("/audit")
    fun getAllApplication(): ResponseEntity<List<TempBusinessResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllApplication())
    }

    @Operation(summary = "사업자 목록 조회")
    @GetMapping("/business")
    fun getAllBusiness(): ResponseEntity<List<BusinessResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllBusiness())
    }

    @Operation(summary = "사업자 단건 조회")
    @GetMapping("/business/{businessId}")
    fun getBusiness(): ResponseEntity<BusinessResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }
}