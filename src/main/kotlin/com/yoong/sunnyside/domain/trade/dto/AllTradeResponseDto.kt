package com.yoong.sunnyside.domain.trade.dto

data class AllTradeResponseDto(
    val userId: Long,
    val communityId: Long,
    val title: String,
    val description: String
)