package com.yoong.sunnyside.domain.notification.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.notification.dto.CreateNotificationRequest
import com.yoong.sunnyside.domain.notification.dto.NotificationResponse
import com.yoong.sunnyside.domain.notification.dto.UpdateNotificationRequest
import com.yoong.sunnyside.domain.notification.service.NotificationService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@Tag(name = "공지사항", description = "공지사항 API")
@RestController
@RequestMapping("/notification")
class NotificationController(
    private val notificationService: NotificationService
) {
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    fun creatNotification(
        @RequestBody request: CreateNotificationRequest,
        @AuthenticationPrincipal adminId: Long
    ): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.createNotification(request, adminId))
    }

    @GetMapping
    fun getNotificationPage(
        @RequestParam title: String?,
        @PageableDefault(page = 0, size = 10, sort = ["createdAt"], direction = Sort.Direction.DESC) pageable: Pageable
    ): ResponseEntity<Page<NotificationResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.getNotificationPage(title, pageable))
    }

    @GetMapping("{notificationId}")
    fun getNotification(@PathVariable("notificationId") notificationId: Long): ResponseEntity<NotificationResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.getNotification(notificationId))
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{notificationId}")
    fun updateNotification(
        @PathVariable("notificationId") notificationId: Long,
        @RequestBody request: UpdateNotificationRequest
    ): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(notificationService.updateNotification(notificationId, request))
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{notificationId}")
    fun deleteNotification(@PathVariable("notificationId") id: Long): ResponseEntity<DefaultResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notificationService.deleteNotification(id))
    }
}