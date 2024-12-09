package com.yoong.sunnyside.domain.consumer.dto

data class ConsumerSignupRequest(
    val email: String,
    val password: String,
    val confirmPassword: String,
    val nickname: String,
    val address: String,
    val phoneNumber: String,
    val languages: List<String>,
    val country: String,
)