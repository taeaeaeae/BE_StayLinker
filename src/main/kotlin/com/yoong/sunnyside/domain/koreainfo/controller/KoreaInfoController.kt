package com.yoong.sunnyside.domain.koreainfo.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.koreainfo.dto.CreateKoreaInfoRequest
import com.yoong.sunnyside.domain.koreainfo.dto.KoreaInfoResponse
import com.yoong.sunnyside.domain.koreainfo.dto.UpdateKoreaInfoRequest
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "한국 정보", description = "한국정보 API")
@RestController
@RequestMapping("/kor-info")
class KoreaInfoController {

    @GetMapping("/{category}/{informationId}")
    fun getKoreaInfo(
        @PathVariable("informationId") id: Long,
        @PathVariable("category") category: String,
    ): ResponseEntity<KoreaInfoResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @GetMapping
    fun getAllKoreaInfo(): ResponseEntity<List<KoreaInfoResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @PostMapping
    fun createKoreaInfo(@RequestBody request: CreateKoreaInfoRequest): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(TODO())
    }

    @PutMapping("/{informationId}")
    fun updateKoreaInfo(
        @PathVariable("informationId") id: Long,
        @RequestBody request: UpdateKoreaInfoRequest
    ): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @DeleteMapping("/{informationId}")
    fun deleteKoreaInfo(@PathVariable("informationId") id: Long): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO())
    }
}