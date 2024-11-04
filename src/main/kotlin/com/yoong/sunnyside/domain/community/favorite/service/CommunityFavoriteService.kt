package com.yoong.sunnyside.domain.community.favorite.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.community.dto.AllCommunityResponseDto
import com.yoong.sunnyside.domain.favorite.service.FavoriteService
import org.springframework.stereotype.Service

@Service
class CommunityFavoriteService: FavoriteService {

    override fun createFavorite(id: Long): DefaultResponseDto {
        TODO()
    }


    override fun deleteFavorite(id: Long): DefaultResponseDto {
        TODO()
    }

    fun getFavorite(): List<AllCommunityResponseDto> {
        TODO()
    }
}