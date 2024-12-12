package com.yoong.sunnyside.domain.notification.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.notification.dto.CreateNotificationRequest
import com.yoong.sunnyside.domain.notification.dto.NotificationResponse
import com.yoong.sunnyside.domain.notification.dto.UpdateNotificationRequest
import com.yoong.sunnyside.domain.notification.entity.Notification
import com.yoong.sunnyside.domain.notification.repository.NotificationRepository
import org.springframework.stereotype.Service

@Service
class NotificationServiceImpl(
    private val notificationRepository: NotificationRepository
) : NotificationService {
    override fun createNotification(
        createNotificationRequest: CreateNotificationRequest,
        adminId: Long
    ): DefaultResponse {
        TODO("Not yet implemented")
    }

    override fun getNotification(notificationId: Long): NotificationResponse {
        TODO("Not yet implemented")
    }

    override fun getNotificationPage(): List<NotificationResponse> {
        TODO("Not yet implemented")
    }

    override fun updateNotification(
        notificationId: Long,
        updateNotificationRequest: UpdateNotificationRequest
    ): DefaultResponse {
        TODO("Not yet implemented")
    }

    override fun deleteNotification(notificationId: Long): DefaultResponse {
        TODO("Not yet implemented")
    }
}