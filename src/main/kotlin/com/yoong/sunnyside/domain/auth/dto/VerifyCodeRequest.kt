package com.yoong.sunnyside.domain.auth.dto

import com.yoong.sunnyside.infra.security.MemberRole

data class VerifyCodeRequest(
    val email: String,
    val role: MemberRole,
    val code: String,
)