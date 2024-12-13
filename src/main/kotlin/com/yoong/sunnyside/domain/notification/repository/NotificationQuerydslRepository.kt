package com.yoong.sunnyside.domain.notification.repository

import com.yoong.sunnyside.domain.notification.entity.Notification

interface NotificationQuerydslRepository {
    fun findPage(pageSize: Long, cursor: Any?, division: String?, keyword: String?): List<Notification>

}