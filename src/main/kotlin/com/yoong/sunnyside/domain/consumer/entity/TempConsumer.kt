package com.yoong.sunnyside.domain.consumer.entity

import com.yoong.sunnyside.domain.consumer.dto.ConsumerSignupRequest
import com.yoong.sunnyside.infra.security.MemberRole
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "temp_consumer")
class TempConsumer(
    @Column(name = "email", nullable = false)
    var email: String,

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

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    var role: MemberRole,

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    constructor(consumerSignupRequest: ConsumerSignupRequest, encodedPassword: String) : this(
        email = consumerSignupRequest.email,
        password = encodedPassword,
        address = consumerSignupRequest.address,
        nickname = consumerSignupRequest.nickname,
        country = consumerSignupRequest.country,
        phoneNumber = consumerSignupRequest.phoneNumber,
        role = MemberRole.CONSUMER,
        foreignNumber = null,
        foreignCreateAt = null
    )

}