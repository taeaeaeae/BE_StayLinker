package com.yoong.sunnyside.domain.favorite.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

interface FavoriteController {

    fun createFavorite(@PathVariable id: Long, ): ResponseEntity<DefaultResponse>

    fun deleteFavorite(@PathVariable id: Long, ): ResponseEntity<DefaultResponse>
}