package com.yoong.sunnyside.domain.consumer.dto

data class PasswordRequestDto(
    val password: String,
    val retryPassword: String
)