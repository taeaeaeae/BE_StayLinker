package com.yoong.sunnyside.domain.blacklist.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.blacklist.dto.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "블랙리스트", description = "블랙리스트 등록 API")
@RestController
@RequestMapping("/blacklist")
class BlackListController {
    @Operation(summary = "사업자 경고 횟수 조회")
    @GetMapping("/business/report/{businessId}")
    fun getBusinessReportCount(): ResponseEntity<BusinessReportCountResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @Operation(summary = "매물 신고 조회")
    @GetMapping("/realEstate/report/{realEstateId}")
    fun getRealEstateReportCount(@PathVariable("realEstateId") id: Long): ResponseEntity<RealEstateReportCountResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @Operation(summary = "사업자 블랙리스트 목록 조회")
    @GetMapping("/business")
    fun getAllBusinessBlacklist(): ResponseEntity<List<BusinessBlackListResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @Operation(summary = "사업자 블랙리스트 등록")
    @PostMapping("/business/{businessId}")
    fun creatBusinessBlacklist(@PathVariable("businessId") id: Long): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(TODO())
    }

    @Operation(summary = "사업자 블랙리스트 삭제")
    @DeleteMapping("/business/{businessId}")
    fun deleteBusinessBlacklist(@PathVariable("businessId") id: Long): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO())
    }

    @Operation(summary = "사용자 블랙리스트 목록 조회")
    @GetMapping("/consumer")
    fun getAllConsumerBlacklist(): ResponseEntity<ConsumerBlackListResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(TODO())
    }

    @Operation(summary = "사용자 블랙리스트 등록")
    @PostMapping("/consumer/{consumerId}")
    fun creatConsumerBlacklist(@PathVariable("consumerId") id: Long): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(TODO())
    }

    @Operation(summary = "사용자 이용정지 등록")
    @PostMapping("/consumer/stop/{consumerId}")
    fun creatConsumerStopUse(@PathVariable("consumerId") id: Long): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(TODO())
    }

    @Operation(summary = "사용자 블랙리스트 삭제")
    @DeleteMapping("/consumer/{consumerId}")
    fun deleteConsumerBlacklist(@PathVariable("consumerId") id: Long): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO())
    }

    @Operation(summary = "사용자 경고 횟수 조회")
    @GetMapping("/consumer/report/{consumerId}")
    fun getConsumerReportCount(@PathVariable("consumerId") id: Long): ResponseEntity<ConsumerReportCountResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @Operation(summary = "커뮤니티 신고 조회")
    @GetMapping("/cumunity/report/{cumunityId}")
    fun getCumunityReportCount(@PathVariable("cumunityId") id: Long): ResponseEntity<ComunityReportCountResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }
}