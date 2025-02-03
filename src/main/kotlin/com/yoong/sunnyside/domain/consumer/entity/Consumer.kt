package com.yoong.sunnyside.domain.consumer.entity

import com.yoong.sunnyside.domain.consumer.dto.AlienRegistrationCardRequest
import com.yoong.sunnyside.domain.consumer.dto.ConsumerSignupRequest
import com.yoong.sunnyside.domain.consumer.dto.ConsumerUpdateRequest
import com.yoong.sunnyside.infra.security.MemberRole
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SQLRestriction
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@SQLRestriction("deleted_at is null")
@Table
class Consumer(
    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "address", nullable = false)
    var address: String,

    @Column(name = "nickname", nullable = false)
    var nickname: String,

    @Column(name = "country", nullable = false)
    var country: String,

    @Column(name = "phone_number", nullable = false)
    var phoneNumber: String,

    @Column(name = "foreign_number")
    var foreignNumber: String,

    @Column(name = "foreign_create_at")
    var foreignCreateAt: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    var role: MemberRole

) {
    fun changePassword(password: String) {
        this.password = password
    }

    fun update(consumerUpdateRequest: ConsumerUpdateRequest) {
        this.address = consumerUpdateRequest.address
        this.nickname = consumerUpdateRequest.nickname
    }

    fun delete() {
        this.deletedAt = LocalDateTime.now()
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at")
    @UpdateTimestamp
    var updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null

    constructor(alienRegistrationCardRequest: AlienRegistrationCardRequest, tempConsumer: TempConsumer) : this (
        email = tempConsumer.email,
        password = tempConsumer.password,
        address = tempConsumer.address,
        nickname = tempConsumer.nickname,
        country = tempConsumer.country,
        phoneNumber = tempConsumer.phoneNumber,
        foreignNumber = alienRegistrationCardRequest.toForeignJiminEncrypt(),
        foreignCreateAt = alienRegistrationCardRequest.toIssueDateEncrypt(),
        role = tempConsumer.role
    )

    //임시 적용
    constructor(consumerSignupRequest: ConsumerSignupRequest, encodedPassword: String): this (
        email = consumerSignupRequest.email,
        password = encodedPassword,
        address = consumerSignupRequest.address,
        nickname = consumerSignupRequest.nickname,
        country = consumerSignupRequest.country,
        phoneNumber = consumerSignupRequest.phoneNumber,
        foreignNumber = "",
        foreignCreateAt = "",
        role = MemberRole.CONSUMER
    )
}