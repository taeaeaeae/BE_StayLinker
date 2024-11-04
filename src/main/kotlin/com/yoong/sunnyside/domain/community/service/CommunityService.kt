package com.yoong.sunnyside.domain.community.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.community.dto.AllCommunityResponseDto
import com.yoong.sunnyside.domain.community.dto.CommunityRequestDto
import com.yoong.sunnyside.domain.community.dto.CommunityResponseDto
import org.springframework.stereotype.Service

@Service
class CommunityService {

    fun createCommunity(communityRequestDto: CommunityRequestDto): DefaultResponseDto {
        TODO()
    }

    fun getAllCommunity(): List<AllCommunityResponseDto> {
        TODO()
    }

    fun getCommunity(communityId: Long): CommunityResponseDto {
        TODO()
    }

    fun updateCommunity(communityRequestDto: CommunityRequestDto, communityId: Long): DefaultResponseDto {
        TODO()
    }

    fun deleteCommunity(communityId: Long): DefaultResponseDto {
        TODO()
    }

    fun reportCommunity(communityId: Long): DefaultResponseDto {
        TODO()
    }
}