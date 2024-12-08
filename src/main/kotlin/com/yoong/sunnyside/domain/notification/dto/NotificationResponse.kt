package com.yoong.sunnyside.domain.notification.dto

import java.time.LocalDateTime

data class NotificationResponse(
    val noticeId: Long,
    val title: String,
    val description: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val division: String
)
