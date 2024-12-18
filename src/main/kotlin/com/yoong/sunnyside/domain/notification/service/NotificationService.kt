package com.yoong.sunnyside.domain.notification.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.notification.dto.CreateNotificationRequest
import com.yoong.sunnyside.domain.notification.dto.NotificationResponse
import com.yoong.sunnyside.domain.notification.dto.UpdateNotificationRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface NotificationService {
    fun createNotification(createNotificationRequest: CreateNotificationRequest, adminId: Long): DefaultResponse
    fun getNotification(notificationId: Long): NotificationResponse
    fun getNotificationPage(title: String?, pageable: Pageable): Page<NotificationResponse>
    fun updateNotification(notificationId: Long, updateNotificationRequest: UpdateNotificationRequest): DefaultResponse
    fun deleteNotification(notificationId: Long): DefaultResponse
}