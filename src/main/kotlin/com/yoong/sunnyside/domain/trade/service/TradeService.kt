package com.yoong.sunnyside.domain.trade.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.trade.dto.AllTradeResponseDto
import com.yoong.sunnyside.domain.trade.dto.TradeRequestDto
import com.yoong.sunnyside.domain.trade.dto.TradeResponseDto
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class TradeService {

    fun createTrade(tradeRequestDto: TradeRequestDto): DefaultResponseDto {
        TODO()
    }

    fun getAllTrade(): List<AllTradeResponseDto> {
        TODO()
    }

    fun getTrade(tradeId: Long): TradeResponseDto {
        TODO()
    }

    fun updateTrade(tradeRequestDto: TradeRequestDto, tradeId: Long): DefaultResponseDto {
        TODO()
    }

    fun deleteTrade(tradeId: Long): DefaultResponseDto {
        TODO()
    }
}