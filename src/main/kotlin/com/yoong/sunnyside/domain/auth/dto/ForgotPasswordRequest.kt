package com.yoong.sunnyside.domain.auth.dto

import com.yoong.sunnyside.infra.security.MemberRole

data class ForgotPasswordRequest(
    val email: String,
    val role: MemberRole,
    val verificationCode: String,
    val newPassword: String,
    val retypeNewPassword: String
)
