package com.yoong.sunnyside.domain.admin.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.admin.dto.CompanionRequest
import com.yoong.sunnyside.domain.admin.service.AdminService
import com.yoong.sunnyside.domain.business.dto.BusinessResponse
import com.yoong.sunnyside.domain.business.dto.TempBusinessResponse
import com.yoong.sunnyside.domain.business.model.Business
import com.yoong.sunnyside.domain.business.model.TempBusiness
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
    @PatchMapping("/regist/{auditId}")
    fun allow(@PathVariable("auditId") id: Long): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.allowBusiness(id))
    }

    @PatchMapping("/companion/{auditId}")
    fun companion(@PathVariable("auditId") id: Long, request: CompanionRequest): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @DeleteMapping("/remove/{auditId}")
    fun deleteAudit(@PathVariable("auditId") id: Long): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO())
    }

    @GetMapping("/audit/{auditId}")
    fun audit(@PathVariable("auditId") aid: Long): ResponseEntity<TempBusinessResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @GetMapping("/audit")
    fun getAllApplication(): ResponseEntity<List<TempBusinessResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllApplication())
    }

    @GetMapping("/business")
    fun getAllBusiness(): ResponseEntity<List<BusinessResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllBusiness())
    }

    @GetMapping("/business/{businessId}")
    fun getBusiness(): ResponseEntity<BusinessResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }
}