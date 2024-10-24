package com.yoong.sunnyside.domain.consumer.model

import jakarta.persistence.*
import org.hibernate.annotations.SQLRestriction
import java.time.LocalDateTime

@Entity
@SQLRestriction("deleted_at=null")
@Table
class Consumer(
    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "address", nullable = false)
    var address: String,

    @Column(name = "nickname", nullable = false)
    var nickname: String,

    @Column(name = "country", nullable = false)
    val country: String,

    @Column(name = "phone_number", nullable = false)
    var phoneNumber: String,

    @Column(name = "foreign_number")
    var foreignNumber: String? = null,

    @Column(name = "foreign_create_at")
    var foreignCreateAt: String? = null,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}