package com.yoong.sunnyside.domain.favorite.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.infra.security.MemberPrincipal
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable

interface FavoriteController {

    fun createFavorite(@PathVariable id: Long, @AuthenticationPrincipal principal: MemberPrincipal): ResponseEntity<DefaultResponse>

    fun deleteFavorite(@PathVariable id: Long, @AuthenticationPrincipal principal: MemberPrincipal): ResponseEntity<DefaultResponse>
}