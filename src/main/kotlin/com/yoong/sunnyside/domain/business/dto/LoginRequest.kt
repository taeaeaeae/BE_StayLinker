package com.yoong.sunnyside.domain.business.dto

import com.yoong.sunnyside.infra.security.MemberRole

data class LoginRequest(
    val type: MemberRole,
    val businessCode: String,
    val password: String
)
