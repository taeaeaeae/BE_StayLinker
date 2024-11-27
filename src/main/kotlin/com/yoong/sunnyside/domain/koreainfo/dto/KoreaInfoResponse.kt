package com.yoong.sunnyside.domain.koreainfo.dto

import java.time.LocalDateTime

data class KoreaInfoResponse(
    val id: String,
    val category: String,
    val title: String,
    val description: String,
    val division: String,
    val image: String,
    val adminId: Long,
    val createdAt: LocalDateTime,
    val updateAt: LocalDateTime
) {
}