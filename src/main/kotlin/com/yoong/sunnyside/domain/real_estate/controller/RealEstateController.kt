package com.yoong.sunnyside.domain.real_estate.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.real_estate.dto.CreateRealEstate
import com.yoong.sunnyside.domain.real_estate.dto.RealEstatePageResponse
import com.yoong.sunnyside.domain.real_estate.dto.RealEstateResponse
import com.yoong.sunnyside.domain.real_estate.dto.UpdateRealEstate
import com.yoong.sunnyside.domain.real_estate.example_data.RealEstateExample
import com.yoong.sunnyside.domain.real_estate.service.RealEstateService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "부동산", description = "부동산 관련 CRUD")
@RestController
@RequestMapping("/real-estate")
class RealEstateController(
    private val realEstateService: RealEstateService
){

    @Operation(
        summary = "부동산 작성 API",
        description = "부동산 데이터를 생성 하는 API",
        responses = [ApiResponse(
            responseCode = "201",
            description = "부동산 옵션도 같이 넣어 주시면 됩니다!!",
            content = [Content(
                examples = [
                    ExampleObject(
                        name = "createRealEstateExample",
                        summary = "생성 예시",
                        value = RealEstateExample.CREATE_REAL_ESTATE_DATA)]
            )]
        )]
    )
    @PostMapping
    fun createRealEstate(
        @RequestBody createRealEstate: CreateRealEstate
    ): ResponseEntity<DefaultResponse>
        = ResponseEntity.status(HttpStatus.CREATED).body(realEstateService.createRealEstate(createRealEstate))


    @Operation(
        summary = "부동산 데이터 상세 조회 API",
        description = "부동산 데이터를 상세 조회하는 API ( 부동산 옵션 포함 )",
        responses = [ApiResponse(
            responseCode = "200",
            description = "부동산 데이터 상세 조회",
            content = [Content(
                examples = [
                    ExampleObject(
                        name = "getRealEstateExample",
                        summary = "응답 예시",
                        value = RealEstateExample.CREATE_REAL_ESTATE_DATA)]
            )]
        )]
    )
    @GetMapping("/{real-estate-id}")
    fun getRealEstate(
        @PathVariable("real-estate-id") realEstateId: Long
    ): ResponseEntity<RealEstateResponse>
        = ResponseEntity.status(HttpStatus.OK).body(realEstateService.getRealEstate(realEstateId))

    @Operation(
        summary = "부동산 데이터 조회 API",
        description = "부동산 데이터를 조회하는 API",
        responses = [ApiResponse(
            responseCode = "200",
            description = "부동산 데이터 상세 조회",
            content = [Content(
                examples = [
                    ExampleObject(
                        name = "getRealEstateExample",
                        summary = "응답 예시",
                        value = RealEstateExample.GET_REAL_ESTATE_DATA_LIST)]
            )]
        )]
    )
    @GetMapping
    fun getRealEstatePage(
        @PageableDefault(size = 10, page = 0) pageable: Pageable
    ): ResponseEntity<Page<RealEstatePageResponse>>
            = ResponseEntity.status(HttpStatus.OK).body(realEstateService.getRealEstatePage(pageable))

    @PutMapping("/{real-estate-id}")
    fun updateRealEstate(
        @PathVariable("real-estate-id") realEstateId: Long,
        @RequestBody updateRealEstate: UpdateRealEstate
    ): ResponseEntity<DefaultResponse>
            = ResponseEntity.status(HttpStatus.OK)
                .body(realEstateService.updateRealEstate(realEstateId, updateRealEstate))

    @DeleteMapping("/{real-estate-id}")
    fun deleteRealEstate(
        @PathVariable("real-estate-id") realEstateId: Long
    ): ResponseEntity<DefaultResponse>
            = ResponseEntity.status(HttpStatus.OK)
                .body(realEstateService.deleteRealEstate(realEstateId))


}