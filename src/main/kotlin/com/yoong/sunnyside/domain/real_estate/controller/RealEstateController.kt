package com.yoong.sunnyside.domain.real_estate.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/real-estate")
class RealEstateController(

){

    @PostMapping
    fun createRealEstate(): ResponseEntity<Unit>
        = ResponseEntity.status(HttpStatus.CREATED).build()

    @GetMapping
    fun getRealEstate(): ResponseEntity<Unit>
        = ResponseEntity.status(HttpStatus.OK).build()

    @GetMapping
    fun getRealEstateList(): ResponseEntity<Unit>
            = ResponseEntity.status(HttpStatus.OK).build()

    @PutMapping
    fun updateRealEstateList(): ResponseEntity<Unit>
            = ResponseEntity.status(HttpStatus.OK).build()

    @DeleteMapping
    fun deleteRealEstateList(): ResponseEntity<Unit>
            = ResponseEntity.status(HttpStatus.OK).build()


}