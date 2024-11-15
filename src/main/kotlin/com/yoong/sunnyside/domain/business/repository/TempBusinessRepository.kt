package com.yoong.sunnyside.domain.business.repository

import com.yoong.sunnyside.domain.business.entity.TempBusiness
import org.springframework.data.jpa.repository.JpaRepository

interface TempBusinessRepository : JpaRepository<TempBusiness, Long> {
    fun existsByBusinessCode(businessCode: String): Boolean
    fun existsByNickName(nickName: String): Boolean
    fun existsByEmail(email: String): Boolean
}