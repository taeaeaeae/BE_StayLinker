package com.yoong.sunnyside.domain.consumer.model

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

class Business(
    @Column(name = "business_code", nullable = false)
    val businessCode : String,

    @Column(name = "business_name", nullable = false)
    val businessName : String,

    @Column(name = "phone_number", nullable = false)
    var phoneNumber : String,

    @Column(name = "email", nullable = false)
    var email : String,

    @Column(name = "password", nullable = false)
    var password : String,

    @Column(name = "address", nullable = false)
    var address : String,

    @Column(name = "business_certificate", nullable = false)
    var businessCertificate : String,

    @Column(name = "nickName", nullable = false)
    var nickName : String,

    @Column(name = "created_at", nullable = false)
    val createdAt : LocalDateTime,

    @Column(name = "updated_at")
    var updatedAt : LocalDateTime? = null,

    @Column(name = "deleted_at")
    var deletedAt : LocalDateTime? = null

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}