package com.yoong.sunnyside.domain.consumer.dto

data class ConsumerUpdateRequest(
    val nickname: String,
    val address: String,
    val languages: List<String>,
)