package com.yoong.sunnyside.domain.Fna.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.faq.dto.CreateFaqRequest
import com.yoong.sunnyside.domain.faq.dto.FaqResponse
import com.yoong.sunnyside.domain.faq.dto.UpdateFnaRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "FAQ", description = "FAQ API")
@RestController
@RequestMapping("/faq")
class FaqController(
) {
    @Operation(summary = "fna 등록")
    @PostMapping
    fun creatFna(@RequestBody request: CreateFaqRequest): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(TODO())
    }

    @Operation(summary = "fna 전체 보기")
    @GetMapping
    fun getAllFna(): ResponseEntity<List<FaqResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @Operation(summary = "fna 단건조회")
    @GetMapping("{fnaId}")
    fun getFna(): ResponseEntity<FaqResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @Operation(summary = "fna 수정하기")
    @PutMapping("{fnaId}")
    fun updateFna(
        @PathVariable("fnaId") id: Long,
        @RequestBody request: UpdateFnaRequest
    ): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @Operation(summary = "fna 삭제하기")
    @DeleteMapping("{fnaId}")
    fun deleteFna(@PathVariable("fnaId") id: Long): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO())
    }
}