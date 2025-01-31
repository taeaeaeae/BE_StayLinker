package com.yoong.sunnyside.domain.koreainfo.dto

data class UpdateKoreaInfoRequest(
    val title: String,
    val content: String,
    val division: String,
    val image: String,
)
