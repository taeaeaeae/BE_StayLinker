package com.yoong.sunnyside.domain.consumer.dto

data class ConsumerSignupRequest(
    val email: String,
    val password: String,
    val nickname: String,
    val address: String,
    val languages: List<String>,
)