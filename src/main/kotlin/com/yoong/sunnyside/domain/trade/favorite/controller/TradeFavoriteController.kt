package com.yoong.sunnyside.domain.trade.favorite.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.favorite.controller.FavoriteController
import com.yoong.sunnyside.domain.trade.dto.AllTradeResponseDto
import com.yoong.sunnyside.domain.trade.favorite.service.TradeFavoriteService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@Tag(name = "중고 거래 찜하기 관련 API")
@RestController
@RequestMapping("/trade/favorite")
class TradeFavoriteController(
    private val tradeFavoriteService: TradeFavoriteService
): FavoriteController {



    @Operation(summary = "중고 거래 찜하기 API", description = "중고 거래 id 값을 넣어 주시면 됩니다")
    @PostMapping("/{id}")
    override fun createFavorite(
        @PathVariable id: Long,
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(tradeFavoriteService.createFavorite(id))

    @Operation(summary = "내 찜한 중고 거래 게시글 조회 API")
    @GetMapping
    fun getFavorites(
    ): ResponseEntity<List<AllTradeResponseDto>>
            = ResponseEntity.status(HttpStatus.OK).body(tradeFavoriteService.getFavorite())

    @Operation(summary = "중고 거래 찜하기 삭제 API", description = "중고 거래 id 값을 넣어 주시면 됩니다")
    @DeleteMapping("/{id}")
    override fun deleteFavorite(
        @PathVariable("id") id: Long,
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(tradeFavoriteService.deleteFavorite(id))
}