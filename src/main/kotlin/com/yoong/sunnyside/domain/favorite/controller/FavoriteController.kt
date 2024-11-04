package com.yoong.sunnyside.domain.favorite.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable

interface FavoriteController {

    fun createFavorite(@PathVariable id: Long, ): ResponseEntity<DefaultResponseDto>

    fun deleteFavorite(@PathVariable id: Long, ): ResponseEntity<DefaultResponseDto>
}