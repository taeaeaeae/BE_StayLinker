package com.yoong.sunnyside.domain.notification.dto

import java.time.LocalDateTime

data class CreateNotificationRequest(
    val noticeId: Long,
    val title: String,
    val description: String,
    val date: LocalDateTime,
    val division: String
)
