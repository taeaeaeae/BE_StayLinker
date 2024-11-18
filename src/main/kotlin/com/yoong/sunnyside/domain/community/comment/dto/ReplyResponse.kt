package com.yoong.sunnyside.domain.community.comment.dto

import com.yoong.sunnyside.domain.community.comment.entity.CommunityReply
import java.time.LocalDateTime

data class ReplyResponse(
    val id: Long,
    val consumerId: Long,
    val description: String,
    val createdAt: LocalDateTime,
) {

    companion object {
        fun from(comment: CommunityReply): ReplyResponse {

            return ReplyResponse(
                id = comment.id!!,
                consumerId = comment.consumer.id!!,
                description = comment.description,
                createdAt = comment.createdAt,
            )
        }
    }
}