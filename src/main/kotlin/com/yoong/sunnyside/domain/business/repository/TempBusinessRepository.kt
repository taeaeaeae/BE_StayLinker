package com.yoong.sunnyside.domain.business.repository

import com.yoong.sunnyside.domain.business.model.TempBusiness
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TempBusinessRepository : JpaRepository<TempBusiness, Long> {
    fun existsBusinessCode(businessCode: String): Boolean
}