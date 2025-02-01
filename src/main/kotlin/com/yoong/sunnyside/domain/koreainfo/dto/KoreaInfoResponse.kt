package com.yoong.sunnyside.domain.koreainfo.dto

import com.yoong.sunnyside.domain.koreainfo.entity.KoreaInfo
import java.time.LocalDateTime

data class KoreaInfoResponse(
    val id: Long,
    val title: String,
    val content: String,
    val division: String,
    val image: String?,
    val adminId: Long,
    val createdAt: LocalDateTime,
    val updateAt: LocalDateTime
) {
    companion object {
        fun from(koreaInfo: KoreaInfo): KoreaInfoResponse {
            return KoreaInfoResponse(
                id = koreaInfo.id!!,
                division = koreaInfo.division,
                title = koreaInfo.title,
                content = koreaInfo.content,
                image = koreaInfo.image,
                adminId = koreaInfo.adminId,
                createdAt = koreaInfo.createdAt,
                updateAt = koreaInfo.updatedAt
            )
        }
    }
}