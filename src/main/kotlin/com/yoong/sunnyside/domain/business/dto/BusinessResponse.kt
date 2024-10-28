package com.yoong.sunnyside.domain.business.dto

import com.yoong.sunnyside.domain.business.model.Business
import java.time.LocalDateTime

data class BusinessResponse(
    val id: Long,
    val businessCode: String,
    val businessName: String,
    val phoneNumber: String,
    val email: String,
    val address: String,
    val businessCertificate: String,
    val nickName: String,
    val createdAt: LocalDateTime,
) {
    companion object {
        fun from(business: Business): BusinessResponse {
            return BusinessResponse(
                id = business.id!!,
                businessCode = business.businessCode,
                businessName = business.businessName,
                phoneNumber = business.phoneNumber,
                email = business.email,
                address = business.address,
                businessCertificate = business.businessCertificate,
                nickName = business.nickName,
                createdAt = business.createdAt
            )
        }
    }
}
