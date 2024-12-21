package com.yoong.sunnyside.domain.notification.dto

import com.yoong.sunnyside.domain.notification.entity.Notification
import java.time.LocalDateTime

data class NotificationResponse(
    val id: Long,
    val title: String,
    val description: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val division: String,
    val adminId: Long
) {

    companion object {
        fun from(notification: Notification): NotificationResponse {
            return NotificationResponse(
                id = notification.id!!,
                title = notification.title,
                description = notification.description,
                createdAt = notification.createdAt,
                updatedAt = notification.updatedAt,
                division = notification.division,
                adminId = notification.adminId
            )
        }
    }
}
