package com.yoong.sunnyside.domain.community.dto

import com.yoong.sunnyside.domain.community.entity.Community

data class AllCommunityResponse(
    val userId: Long,
    val communityId: Long,
    val title: String,
    val description: String
){
    companion object {
        fun from(community: Community): AllCommunityResponse {
            return AllCommunityResponse(
                userId = community.consumerId,
                communityId = community.id!!,
                title = community.title,
                description = community.description
            )
        }
    }
}