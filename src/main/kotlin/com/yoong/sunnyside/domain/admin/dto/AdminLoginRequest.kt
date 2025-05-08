package com.yoong.sunnyside.domain.admin.dto

import com.yoong.sunnyside.infra.security.MemberRole

data class AdminLoginRequest(
    val type: MemberRole,
    val email: String,
    val password: String
)
