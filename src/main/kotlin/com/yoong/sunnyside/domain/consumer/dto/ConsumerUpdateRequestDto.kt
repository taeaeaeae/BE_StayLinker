package com.yoong.sunnyside.domain.consumer.dto

data class ConsumerUpdateRequestDto(
    val nickname: String,
    val address: String,
    val languages: List<String>,
)