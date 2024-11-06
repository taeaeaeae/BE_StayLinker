package com.yoong.sunnyside.domain.kor_info.controller

import com.yoong.sunnyside.domain.kor_info.dto.KoreaInfoResponseDto
import com.yoong.sunnyside.domain.kor_info.service.KoreaInfoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kor-info")
class KoreaInfoController(
    private val koreaInfoService: KoreaInfoService
){

    @GetMapping
    fun getKoreaInfos():ResponseEntity<List<KoreaInfoResponseDto>>
        = ResponseEntity.status(HttpStatus.OK).body(koreaInfoService.getKoreaInfos())

    @GetMapping("/{infoId}")
    fun getKoreaInfo(
        @PathVariable infoId: Long
    ):ResponseEntity<KoreaInfoResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(koreaInfoService.getKoreaInfo(infoId))

}