package com.yoong.sunnyside.domain.business.repository

import com.yoong.sunnyside.domain.business.model.TempBusiness
import org.springframework.data.jpa.repository.JpaRepository

interface TempBusinessRepository : JpaRepository<TempBusiness, Long> {
    fun existsByBusinessCode(businessCode: String): Boolean
    fun findByBusinessCode(businessCode: String): TempBusiness?
}