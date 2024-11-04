package com.yoong.sunnyside.domain.favorite.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto

interface FavoriteService {

    fun createFavorite(id: Long): DefaultResponseDto

    fun deleteFavorite(id: Long): DefaultResponseDto
}