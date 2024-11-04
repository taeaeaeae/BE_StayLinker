package com.yoong.sunnyside.domain.trade.favorite.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.trade.dto.AllTradeResponseDto
import org.springframework.stereotype.Service

@Service
class TradeFavoriteService {

    fun createFavorite(communityId: Long): DefaultResponseDto {
        TODO()
    }


    fun deleteFavorite(replyId: Long): DefaultResponseDto {
        TODO()
    }

    fun getFavorite(): List<AllTradeResponseDto> {
        TODO()
    }
}