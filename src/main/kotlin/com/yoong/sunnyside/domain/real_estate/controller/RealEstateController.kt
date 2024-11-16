package com.yoong.sunnyside.domain.real_estate.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.real_estate.dto.CreateRealEstate
import com.yoong.sunnyside.domain.real_estate.dto.RealEstatePageResponse
import com.yoong.sunnyside.domain.real_estate.dto.RealEstateResponse
import com.yoong.sunnyside.domain.real_estate.dto.UpdateRealEstate
import com.yoong.sunnyside.domain.real_estate.service.RealEstateService
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

    @PostMapping
    fun createRealEstate(
        @RequestBody createRealEstate: CreateRealEstate
    ): ResponseEntity<DefaultResponse>
        = ResponseEntity.status(HttpStatus.CREATED).body(realEstateService.createRealEstate(createRealEstate))

    @GetMapping("/{real-estate-id}")
    fun getRealEstate(
        @PathVariable("real-estate-id") realEstateId: Long
    ): ResponseEntity<RealEstateResponse>
        = ResponseEntity.status(HttpStatus.OK).body(realEstateService.getRealEstate(realEstateId))

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