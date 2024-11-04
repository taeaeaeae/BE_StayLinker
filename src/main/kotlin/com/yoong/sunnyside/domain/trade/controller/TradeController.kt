package com.yoong.sunnyside.domain.trade.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.trade.dto.AllTradeResponseDto
import com.yoong.sunnyside.domain.trade.dto.TradeRequestDto
import com.yoong.sunnyside.domain.trade.dto.TradeResponseDto
import com.yoong.sunnyside.domain.trade.service.TradeService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "중고 거래 관련 API", description = "중고 거래 댓글 API는 따로 관리 중 입니다")
@RestController
@RequestMapping("/trade")
class TradeController(
    private val tradeService: TradeService
){

    @Operation(summary = "중고 거래글 생성 API")
    @PostMapping
    fun createTrade(
        @RequestBody tradeRequestDto: TradeRequestDto
    ): ResponseEntity<DefaultResponseDto>
        = ResponseEntity.status(HttpStatus.OK).body(tradeService.createTrade(tradeRequestDto))

    @Operation(summary = "중고 거래글 전체 조회 API")
    @GetMapping
    fun getAllTrade(): ResponseEntity<List<AllTradeResponseDto>>
            = ResponseEntity.status(HttpStatus.OK).body(tradeService.getAllTrade())

    @Operation(summary = "중고 거래 특정 글 조회 API", description = "중고 거래글 id 값을 넣어 주시면 됩니다")
    @GetMapping("/{tradeId}")
    fun getTrade(
        @PathVariable("tradeId") tradeId: Long,
    ): ResponseEntity<TradeResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(tradeService.getTrade(tradeId))

    @Operation(summary = "중고 거래글 수정 API", description = "중고 거래글 값을 넣어 주시면 됩니다")
    @PutMapping("/{tradeId}")
    fun updateTrade(
        @PathVariable("tradeId") tradeId: Long,
        @RequestBody tradeRequestDto: TradeRequestDto
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(tradeService.updateTrade(tradeRequestDto, tradeId))

    @Operation(summary = "중고 거래글 삭제 API", description = "중고 거래글 id 값을 넣어 주시면 됩니다")
    @DeleteMapping("/{tradeId}")
    fun deleteTrade(
        @PathVariable("tradeId") tradeId: Long,
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(tradeService.deleteTrade(tradeId))
}