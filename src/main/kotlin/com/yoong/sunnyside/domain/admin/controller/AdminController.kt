package com.yoong.sunnyside.domain.admin.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.admin.service.AdminService
import com.yoong.sunnyside.domain.business.dto.BusinessResponse
import com.yoong.sunnyside.domain.business.dto.TempBusinessResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "관리자", description = "관리자 전용 API")
@RestController
@RequestMapping("/admin")
class AdminController(
    private val adminService: AdminService
) {
    @PostMapping("/business/allow/{businessId}")
    fun allow(@PathVariable("businessId") id: Long): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.allowBusiness(id))
    }

    @GetMapping("/business/application")
    fun getAllApplication(): ResponseEntity<List<TempBusinessResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllApplication())
    }

    @GetMapping("/business/all")
    fun getAllBusiness(): ResponseEntity<List<BusinessResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllBusiness())
    }
}