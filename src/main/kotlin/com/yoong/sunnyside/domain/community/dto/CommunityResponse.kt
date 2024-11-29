package com.yoong.sunnyside.domain.community.dto

import java.time.LocalDateTime

data class CommunityResponse(
    val userId: Long,
    val postId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val title: String,
    val description: String,
    val image: String
)