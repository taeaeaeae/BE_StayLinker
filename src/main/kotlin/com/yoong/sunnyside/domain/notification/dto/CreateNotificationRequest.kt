package com.yoong.sunnyside.domain.notification.dto

data class CreateNotificationRequest(
    val adminId: Long,
    val title: String,
    val description: String,
    val division: String
)
