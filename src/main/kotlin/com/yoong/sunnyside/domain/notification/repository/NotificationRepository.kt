package com.yoong.sunnyside.domain.notification.repository

import com.yoong.sunnyside.domain.notification.entity.Notification
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository : JpaRepository<Notification, Long>, NotificationQuerydslRepository {
}