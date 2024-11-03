package com.yoong.sunnyside.domain.community.dto

data class AllCommunityResponseDto(
    val userId: Long,
    val communityId: Long,
    val title: String,
    val description: String
)