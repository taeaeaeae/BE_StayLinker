package com.yoong.sunnyside.domain.business.repository

import com.yoong.sunnyside.domain.business.model.Business
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BusinessRepository : JpaRepository<Business, Long> {
    fun existsBusinessCode(businessCode: String): Boolean
    fun findByBusinessCode(businessCode: String): Business?
    fun existsEmail(email: String): Boolean
    fun findByEmail(email: String): Business?
}