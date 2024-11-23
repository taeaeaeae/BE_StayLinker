package com.yoong.sunnyside.domain.faq.dto

import java.time.LocalDateTime

data class FaqResponse(
    val noticeId: Long,
    val title: String,
    val description: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val division: String
)
