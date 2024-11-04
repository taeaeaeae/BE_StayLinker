package com.yoong.sunnyside.domain.trade.favorite.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.favorite.service.FavoriteService
import com.yoong.sunnyside.domain.trade.dto.AllTradeResponseDto
import org.springframework.stereotype.Service

@Service
class TradeFavoriteService: FavoriteService {

    override fun createFavorite(id: Long): DefaultResponseDto {
        TODO()
    }


    override fun deleteFavorite(id: Long): DefaultResponseDto {
        TODO()
    }

    fun getFavorite(): List<AllTradeResponseDto> {
        TODO()
    }
}