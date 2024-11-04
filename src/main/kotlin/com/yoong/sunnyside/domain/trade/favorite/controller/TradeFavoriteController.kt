package com.yoong.sunnyside.domain.trade.favorite.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.trade.dto.AllTradeResponseDto
import com.yoong.sunnyside.domain.trade.favorite.service.TradeFavoriteService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@Tag(name = "커뮤니티 찜하기 관련 API")
@RestController
@RequestMapping("/community/reply")
class TradeFavoriteController(
    private val tradeFavoriteService: TradeFavoriteService
){

    @Operation(summary = "커뮤니티 댓글 생성 API", description = "커뮤니티 id 값을 넣어 주시면 됩니다")
    @PostMapping("/{tradeId}")
    fun createFavorite(
        @PathVariable tradeId: Long,
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(tradeFavoriteService.createFavorite(tradeId))

    @Operation(summary = "내 찜한 커뮤니티 게시글 조회 API")
    @GetMapping
    fun getFavorite(
    ): ResponseEntity<List<AllTradeResponseDto>>
            = ResponseEntity.status(HttpStatus.OK).body(tradeFavoriteService.getFavorite())

    @Operation(summary = "커뮤니티 찜하기 삭제 API", description = "커뮤니티 id 값을 넣어 주시면 됩니다")
    @DeleteMapping("/{tradeId}")
    fun deleteFavorite(
        @PathVariable("tradeId") tradeId: Long,
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(tradeFavoriteService.deleteFavorite(tradeId))
}