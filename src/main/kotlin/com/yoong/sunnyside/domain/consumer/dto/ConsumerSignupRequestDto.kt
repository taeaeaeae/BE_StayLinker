package com.yoong.sunnyside.domain.consumer.dto

data class ConsumerSignupRequestDto(
    val email: String,
    val password: String,
    val nickname: String,
    val address: String,
    val languages: List<String>,
)