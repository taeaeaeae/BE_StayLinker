package com.yoong.sunnyside.domain.notification.entity

import com.yoong.sunnyside.domain.notification.dto.CreateNotificationRequest
import com.yoong.sunnyside.domain.notification.dto.UpdateNotificationRequest
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SQLRestriction
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "notification")
@SQLRestriction("deleted_at is null")
class Notification(
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "description", nullable = false)
    var description: String,

    @Column(name = "admin_id", nullable = false)
    var adminId: Long,

    @Column(name = "division", nullable = false)
    var division: String,
) {

    constructor(request: CreateNotificationRequest, adminId: Long) : this(
        title = request.title,
        description = request.description,
        adminId = adminId,
        division = request.division
    )

    fun update(request: UpdateNotificationRequest) {
        title = request.title
        description = request.description
    }

    fun delete() {
        deletedAt = LocalDateTime.now()
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "deleted_at", nullable = true)
    var deletedAt: LocalDateTime? = null
}