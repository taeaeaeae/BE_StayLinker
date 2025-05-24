package com.yoong.sunnyside.domain.business.dto

import java.time.LocalDateTime

data class BusinessSearchResultResponse(
    val businessName: String,
    val address: String,
    val businessCertificate: String,
    val agentName: String
)


