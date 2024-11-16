package com.yoong.sunnyside.domain.community.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.community.dto.AllCommunityResponse
import com.yoong.sunnyside.domain.community.dto.CommunityRequest
import com.yoong.sunnyside.domain.community.dto.CommunityResponse
import org.springframework.stereotype.Service

@Service
class CommunityService {

    fun createCommunity(communityRequest: CommunityRequest): DefaultResponse {
        TODO()
    }

    fun getAllCommunity(): List<AllCommunityResponse> {
        TODO()
    }

    fun getCommunity(communityId: Long): CommunityResponse {
        TODO()
    }

    fun updateCommunity(communityRequest: CommunityRequest, communityId: Long): DefaultResponse {
        TODO()
    }

    fun deleteCommunity(communityId: Long): DefaultResponse {
        TODO()
    }

    fun reportCommunity(communityId: Long): DefaultResponse {
        TODO()
    }
}