package com.yoong.sunnyside.domain.community.dto

import com.yoong.sunnyside.domain.community.entity.Community

data class CommunityListResponse(
    val userId: Long,
    val communityId: Long,
    val title: String,
    val description: String,
    val favoriteCount: Long,
){
    companion object{
        fun from(posts: CommunityProjectionDto): CommunityListResponse{
            return CommunityListResponse(
                userId = posts.community.consumerId,
                communityId = posts.community.id!!,
                title = posts.community.title,
                description = posts.community.description,
                favoriteCount = posts.favoriteCount
            )
        }
    }
}
