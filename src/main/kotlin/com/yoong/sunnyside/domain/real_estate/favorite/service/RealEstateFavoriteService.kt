package com.yoong.sunnyside.domain.real_estate.favorite.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.favorite.service.FavoriteService
import org.springframework.stereotype.Service

@Service
class RealEstateFavoriteService: FavoriteService {

    override fun createFavorite(id: Long, memberId: Long): DefaultResponse {
        TODO("Not yet implemented")
    }

    override fun deleteFavorite(id: Long, memberId: Long): DefaultResponse {
        TODO("Not yet implemented")
    }
}