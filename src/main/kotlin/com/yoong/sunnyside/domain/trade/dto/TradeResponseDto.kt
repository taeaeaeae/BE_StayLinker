package com.yoong.sunnyside.domain.trade.dto

import java.time.LocalDateTime

data class TradeResponseDto(
    val userId: Long,
    val postId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val title: String,
    val description: String,
    val image: String
)