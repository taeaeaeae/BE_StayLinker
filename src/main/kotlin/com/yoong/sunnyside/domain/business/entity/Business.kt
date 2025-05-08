package com.yoong.sunnyside.domain.business.entity

import com.yoong.sunnyside.domain.business.dto.BusinessSignupRequest
import com.yoong.sunnyside.infra.security.MemberRole
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

    @Column(name = "registration_code", nullable = false)
    val registrationCode: String,

    @Column(name = "agent_name")
    val agentName: String,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Column(name = "address", nullable = false)
    var address: String,

    @Column(name = "business_certificate", nullable = false)
    var businessCertificate: String,

    @Column(name = "nickname", nullable = false)
    var nickName: String,

    @Column(name = "openingdate", nullable = false)
    var openingDate: String = "",

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    var role: MemberRole = MemberRole.TEMP_BUSINESS

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
            request: BusinessSignupRequest,
            password: String
        ): Business {
            return Business(
                businessCode = request.businessCode,
                businessName = request.businessName,
                registrationCode = request.registrationCode,
                agentName = request.agentName,
                phoneNumber = request.phoneNumber,
                email = request.email,
                password = password,
                address = request.address,
                businessCertificate = request.businessCertificate,
                nickName = request.nickName,
                openingDate = request.openingDate
            )
        }
    }

    fun allowBusiness() {
        role = MemberRole.BUSINESS
    }

    fun passwdChange(passwd: String) {
        password = passwd
    }
}