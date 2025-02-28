package com.yoong.sunnyside.domain.admin.entity

import com.yoong.sunnyside.domain.admin.dto.AdminSignupRequest
import com.yoong.sunnyside.domain.admin.enum_class.AdminStatus
import com.yoong.sunnyside.infra.security.MemberRole
import jakarta.persistence.*

@Entity
@Table(name = "admin")
class Admin(

    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    val role: MemberRole = MemberRole.ADMIN,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    var status: AdminStatus = AdminStatus.DEACTIVATE
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun changePassword(password: String) {
        this.password = password
    }


    companion object {
        fun from(email: String, password: String): Admin {
            return Admin(
                email = email,
                password = password
            )
        }
    }
}