package com.yoong.sunnyside.domain.favorite.service

import com.yoong.sunnyside.common.dto.DefaultResponse

interface FavoriteService {

    fun createFavorite(id: Long, memberId: Long): DefaultResponse

    fun deleteFavorite(id: Long, memberId: Long): DefaultResponse
}