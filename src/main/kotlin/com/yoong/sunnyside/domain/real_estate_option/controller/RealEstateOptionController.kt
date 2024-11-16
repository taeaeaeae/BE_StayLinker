package com.yoong.sunnyside.domain.real_estate_option.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.real_estate_option.dto.CreateRealEstateList
import com.yoong.sunnyside.domain.real_estate_option.dto.DeleteRealEstateOption
import com.yoong.sunnyside.domain.real_estate_option.service.RealEstateOptionService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "부동산 옵션", description = "부동산 옵션 관련 API (생성과 조회는 부동산 API와 중복)")
@RestController
@RequestMapping("/real-estate/option")
class RealEstateOptionController(
    private val realEstateOptionService: RealEstateOptionService
){

    @PostMapping("/{realEstateId}")
    fun updateRealEstateOption(
        @PathVariable("realEstateId") realEstateId: Long,
        @RequestBody createRealEstateList: CreateRealEstateList
    ): ResponseEntity<DefaultResponse> = ResponseEntity.status(HttpStatus.OK).body(realEstateOptionService.updateRealEstateOption(realEstateId, createRealEstateList))

    @DeleteMapping("/{realEstateId}")
    fun deleteRealEstateOption(
        @PathVariable("realEstateId") realEstateId: Long,
        @RequestBody deleteRealEstateOption: DeleteRealEstateOption
    ): ResponseEntity<DefaultResponse> = ResponseEntity.status(HttpStatus.OK).body(realEstateOptionService.deleteRealEstateOption(realEstateId, deleteRealEstateOption))
}