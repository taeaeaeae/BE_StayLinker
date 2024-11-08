package com.yoong.sunnyside.domain.auth.dto

import com.yoong.sunnyside.infra.security.MemberRole

data class EmailResponseDto(
    val isApproved: Boolean,
    val role: MemberRole
)