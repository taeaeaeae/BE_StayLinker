package com.yoong.sunnyside.domain.community.comment.dto

import com.yoong.sunnyside.domain.community.comment.entity.CommunityComment
import java.time.LocalDateTime
import javax.xml.stream.events.Comment

data class CommentResponse(
    val id: Long,
    val customerId: Long,
    val description: String,
    val createdAt: LocalDateTime,
) {

    companion object {
        fun from(comment: CommunityComment): CommentResponse {

            return CommentResponse(
                id = comment.id!!,
                customerId = comment.consumer.id!!,
                description = comment.description,
                createdAt = comment.createdAt,
            )
        }
    }
}