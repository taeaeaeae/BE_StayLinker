package com.yoong.sunnyside.domain.koreainfo.dto

data class CreateKoreaInfoRequest(
    val category: String,
    val title: String,
    val description: String,
    val division: String,
    val image: String,
)
