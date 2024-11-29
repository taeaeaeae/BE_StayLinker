package com.yoong.sunnyside.domain.business.dto

data class BusinessModifyRequest(
    val phoneNumber: String,
    val businessName: String,
    val email: String,
    val address: String,
    val language: List<String>
)
