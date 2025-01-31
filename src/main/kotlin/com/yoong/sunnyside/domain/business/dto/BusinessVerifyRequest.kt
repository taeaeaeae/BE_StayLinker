package com.yoong.sunnyside.domain.business.dto

data class BusinessVerifyRequest(
    val businessNumber: String,
    val registrationNumber: String,
    val name: String,
    val agentName: String,
    val registDate: String
)
