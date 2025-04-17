package com.yoong.sunnyside.domain.business.repository

import com.yoong.sunnyside.domain.business.entity.Business
import org.springframework.data.jpa.repository.JpaRepository

interface BusinessRepository : JpaRepository<Business, Long> {
    fun existsByBusinessCode(businessCode: String): Boolean
    fun findByBusinessCodeAndDeletedAtIsNull(businessCode: String): Business?
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Business?
}