package com.yoong.sunnyside.domain.admin.repository

import com.yoong.sunnyside.domain.admin.entity.Admin
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository: JpaRepository<Admin, Long> {
    fun findByEmail(email: String): Admin?
}