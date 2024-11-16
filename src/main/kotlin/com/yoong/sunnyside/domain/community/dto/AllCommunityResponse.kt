package com.yoong.sunnyside.domain.community.dto

data class AllCommunityResponse(
    val userId: Long,
    val communityId: Long,
    val title: String,
    val description: String
)