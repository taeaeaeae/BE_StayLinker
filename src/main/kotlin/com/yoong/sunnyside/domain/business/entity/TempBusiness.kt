package com.yoong.sunnyside.domain.business.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table
class TempBusiness(
    @Column(name = "business_code", nullable = false)
    val businessCode: String,

    @Column(name = "business_name", nullable = false)
    val businessName: String,

    @Column(name = "phone_number", nullable = false)
    var phoneNumber: String,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "address", nullable = false)
    var address: String,

    @Column(name = "business_certificate", nullable = false)
    var businessCertificate: String,

    @Column(name = "nickName", nullable = false)
    var nickName: String,

    @Column(name = "is_approved")
    var isApproved: Boolean? = null,


    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null

    companion object {
        fun from(
            businessCode: String,
            businessName: String,
            phoneNumber: String,
            email: String,
            password: String,
            address: String,
            businessCertificate: String,
            nickName: String
        ): TempBusiness {
            return TempBusiness(
                businessCode = businessCode,
                businessName = businessName,
                phoneNumber = phoneNumber,
                email = email,
                password = password,
                address = address,
                businessCertificate = businessCertificate,
                nickName = nickName
            )
        }
    }
}