package com.yoong.sunnyside.domain.admin.entity

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
    val role: MemberRole,
){
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun changePassword(password: String) {
        this.password = password
    }
}