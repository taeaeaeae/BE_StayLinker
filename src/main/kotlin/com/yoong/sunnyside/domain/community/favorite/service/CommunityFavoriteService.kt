package com.yoong.sunnyside.domain.community.favorite.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.community.dto.AllCommunityResponse
import com.yoong.sunnyside.domain.favorite.service.FavoriteService
import org.springframework.stereotype.Service

@Service
class CommunityFavoriteService: FavoriteService {

    override fun createFavorite(id: Long): DefaultResponse {
        TODO()
    }


    override fun deleteFavorite(id: Long): DefaultResponse {
        TODO()
    }

    fun getFavorite(): List<AllCommunityResponse> {
        TODO()
    }
}