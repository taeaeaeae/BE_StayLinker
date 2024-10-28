package com.yoong.sunnyside.domain.business.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table
class Business(
    @Column(name = "business_code", nullable = false, unique = true)
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

    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null

    companion object {
        fun from(
            tempBusiness: TempBusiness
        ): Business {
            return Business(
                businessCode = tempBusiness.businessCode,
                businessName = tempBusiness.businessName,
                phoneNumber = tempBusiness.phoneNumber,
                email = tempBusiness.email,
                password = tempBusiness.password,
                address = tempBusiness.address,
                businessCertificate = tempBusiness.businessCertificate,
                nickName = tempBusiness.nickName
            )
        }
    }

    fun passwdChange(passwd: String) {
        password = passwd
    }
}