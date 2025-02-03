package com.yoong.sunnyside.domain.koreainfo.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.enum_class.SearchType
import com.yoong.sunnyside.domain.koreainfo.dto.CreateKoreaInfoRequest
import com.yoong.sunnyside.domain.koreainfo.dto.KoreaInfoResponse
import com.yoong.sunnyside.domain.koreainfo.dto.UpdateKoreaInfoRequest
import com.yoong.sunnyside.domain.koreainfo.service.KoreaInfoService
import com.yoong.sunnyside.infra.security.MemberPrincipal
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
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
class KoreaInfoController(
    private val koreaInfoService: KoreaInfoService
) {

    @GetMapping("/{category}/{informationId}")
    fun getKoreaInfo(
        @PathVariable("informationId") informationId: Long,
    ): ResponseEntity<KoreaInfoResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(koreaInfoService.getKoreaInfo(informationId))
    }

    @GetMapping
    fun getAllKoreaInfo(
        pageable: Pageable,
        division: String?,
        searchType: SearchType?,
        keyword: String?
    ): ResponseEntity<Page<KoreaInfoResponse>> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(koreaInfoService.getKoreaInfoPage(pageable, division, searchType, keyword))
    }

    @PostMapping
    fun createKoreaInfo(
        @RequestBody request: CreateKoreaInfoRequest,
        @AuthenticationPrincipal admin: MemberPrincipal
    ): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(koreaInfoService.createKoreaInfo(request, admin.id))
    }

    @PutMapping("/{informationId}")
    fun updateKoreaInfo(
        @PathVariable("informationId") informationId: Long,
        @RequestBody request: UpdateKoreaInfoRequest
    ): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(koreaInfoService.updateKoreaInfo(informationId, request))
    }

    @DeleteMapping("/{informationId}")
    fun deleteKoreaInfo(@PathVariable("informationId") informationId: Long): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(koreaInfoService.deleteKoreaInfo(informationId))
    }
}