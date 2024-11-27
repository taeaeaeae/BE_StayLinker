package com.yoong.sunnyside.domain.Fna.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.faq.dto.CreateFaqRequest
import com.yoong.sunnyside.domain.faq.dto.FaqResponse
import com.yoong.sunnyside.domain.faq.dto.UpdateFnaRequest
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "FAQ", description = "FAQ API")
@RestController
@RequestMapping("/faq")
class FaqController(
) {
    @PostMapping
    fun creatFna(@RequestBody request: CreateFaqRequest): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(TODO())
    }

    @GetMapping
    fun getAllFna(): ResponseEntity<List<FaqResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @GetMapping
    fun getFna(): ResponseEntity<FaqResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @PutMapping("{fnaId}")
    fun updateFna(
        @PathVariable("fnaId") id: Long,
        @RequestBody request: UpdateFnaRequest
    ): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @DeleteMapping("{fnaId}")
    fun deleteFna(@PathVariable("fnaId") id: Long): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO())
    }
}