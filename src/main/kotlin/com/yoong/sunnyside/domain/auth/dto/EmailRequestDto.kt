package com.yoong.sunnyside.domain.auth.dto

import com.yoong.sunnyside.infra.security.MemberRole

data class EmailRequestDto(
    val email: String,
    val role: MemberRole,
)