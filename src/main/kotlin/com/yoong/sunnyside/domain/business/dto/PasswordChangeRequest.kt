package com.yoong.sunnyside.domain.business.dto

data class PasswordChangeRequest(
    val password: String,
    val retryPassword: String,
)