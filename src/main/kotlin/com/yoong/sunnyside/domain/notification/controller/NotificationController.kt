package com.yoong.sunnyside.domain.notification.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.notification.dto.CreateNotificationRequest
import com.yoong.sunnyside.domain.notification.dto.NotificationResponse
import com.yoong.sunnyside.domain.notification.dto.UpdateNotificationRequest
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "공지사항", description = "공지사항 API")
@RestController
@RequestMapping("/notification")
class NotificationController(
) {
    @PostMapping
    fun creatNotification(@RequestBody request: CreateNotificationRequest): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(TODO())
    }

    @GetMapping
    fun getNotification(): ResponseEntity<List<NotificationResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @PutMapping("{notificationId}")
    fun updateNotification(
        @PathVariable("notificationId") id: Long,
        @RequestBody request: UpdateNotificationRequest
    ): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.OK).body(TODO())
    }

    @DeleteMapping("{notificationId}")
    fun deleteNotification(@PathVariable("notificationId") id: Long): ResponseEntity<DefaultResponseDto> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(TODO())
    }
}