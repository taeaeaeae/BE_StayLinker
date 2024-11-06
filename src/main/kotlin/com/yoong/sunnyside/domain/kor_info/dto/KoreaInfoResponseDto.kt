package com.yoong.sunnyside.domain.kor_info.dto

data class KoreaInfoResponseDto(
    val id: Long,
    val category: String,
    val title: String,
    val description: String,
    val image: List<String>,
)