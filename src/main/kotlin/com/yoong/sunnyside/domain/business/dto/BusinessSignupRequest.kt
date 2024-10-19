package com.yoong.sunnyside.domain.business.dto

data class BusinessSignupRequest(
    val businessCode: String,
    val businessName: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val address: String,
    val businessCertificate: String,
    val nickName: String,
)