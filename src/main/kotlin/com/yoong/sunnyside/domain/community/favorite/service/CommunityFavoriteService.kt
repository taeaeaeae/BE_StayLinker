package com.yoong.sunnyside.domain.community.reply.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.community.dto.AllCommunityResponseDto
import org.springframework.stereotype.Service

@Service
class CommunityFavoriteService {

    fun createFavorite(communityId: Long): DefaultResponseDto {
        TODO()
    }


    fun deleteFavorite(replyId: Long): DefaultResponseDto {
        TODO()
    }

    fun getFavorite(): List<AllCommunityResponseDto> {
        TODO()
    }
}