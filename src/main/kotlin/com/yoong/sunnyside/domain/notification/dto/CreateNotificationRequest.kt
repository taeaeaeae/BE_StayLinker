package com.yoong.sunnyside.domain.notification.dto

data class CreateNotificationRequest(
    val title: String,
    val description: String,
    val division: String
)
