package com.yoong.sunnyside.domain.real_estate.favorite.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.favorite.controller.FavoriteController
import com.yoong.sunnyside.domain.real_estate.favorite.service.RealEstateFavoriteService
import com.yoong.sunnyside.infra.security.MemberPrincipal
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/real-estate/favorite/{realEstateId}")
class RealEstateFavoriteController(
    private val realEstateFavoriteService: RealEstateFavoriteService
): FavoriteController{

    @PostMapping
    override fun createFavorite(
        @PathVariable("realEstateId") id: Long,
        @AuthenticationPrincipal principal: MemberPrincipal
    ): ResponseEntity<DefaultResponse>
        = ResponseEntity.status(HttpStatus.OK).body(realEstateFavoriteService.createFavorite(id, principal.id))

    @DeleteMapping
    override fun deleteFavorite(
        @PathVariable("realEstateId") id: Long,
        @AuthenticationPrincipal principal: MemberPrincipal
    ): ResponseEntity<DefaultResponse>
        = ResponseEntity.status(HttpStatus.OK).body(realEstateFavoriteService.deleteFavorite(id, principal.id))
}