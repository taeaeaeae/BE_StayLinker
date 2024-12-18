package com.yoong.sunnyside.domain.notification.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.ModelNotFoundException
import com.yoong.sunnyside.domain.notification.dto.CreateNotificationRequest
import com.yoong.sunnyside.domain.notification.dto.NotificationResponse
import com.yoong.sunnyside.domain.notification.dto.UpdateNotificationRequest
import com.yoong.sunnyside.domain.notification.entity.Notification
import com.yoong.sunnyside.domain.notification.repository.NotificationRepository
import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import com.yoong.sunnyside.domain.real_estate_option.entity.RealEstateOption
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NotificationServiceImpl(
    private val notificationRepository: NotificationRepository,
//    private val adminRepository: AdminRepository
) : NotificationService {
    @Transactional
    override fun createNotification(
        createNotificationRequest: CreateNotificationRequest,
        adminId: Long
    ): DefaultResponse {

        notificationRepository.save(
            Notification(createNotificationRequest, adminId)
        )

        return DefaultResponse("공지 생성이 완료 되었습니다")
    }

    override fun getNotification(notificationId: Long): NotificationResponse {
        val notification = notificationRepository.findByIdOrNull(notificationId)
            ?: throw ModelNotFoundException("{notificationId}가 존재하지 않아요")
        return NotificationResponse.from(notification)
    }

    override fun getNotificationPage(title: String?, pageable: Pageable): Page<NotificationResponse> {
        val notificationPage = notificationRepository.findAllByTitleContains(title, pageable)
        return notificationPage.map { NotificationResponse.from(it) }

    }

    @Transactional
    override fun updateNotification(
        notificationId: Long,
        updateNotificationRequest: UpdateNotificationRequest
    ): DefaultResponse {
        val notification = notificationRepository.findByIdOrNull(notificationId)
            ?: throw ModelNotFoundException("{notificationId}가 존재하지 않아요")
        notification.update(updateNotificationRequest)
        return DefaultResponse("공지 수정이 완료 되었습니다")
    }

    @Transactional
    override fun deleteNotification(notificationId: Long): DefaultResponse {
        val notification = notificationRepository.findByIdOrNull(notificationId)
            ?: throw ModelNotFoundException("{notificationId}가 존재하지 않아요")
        notificationRepository.delete(notification)
        return DefaultResponse("공지 삭제가 완료되었습니다.")
    }
}