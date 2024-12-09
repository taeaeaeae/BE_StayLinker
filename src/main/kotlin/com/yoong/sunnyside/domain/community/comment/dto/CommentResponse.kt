package com.yoong.sunnyside.domain.community.comment.dto

import com.yoong.sunnyside.domain.community.comment.entity.CommunityComment
import com.yoong.sunnyside.domain.community.comment.entity.CommunityReply
import java.time.LocalDateTime

data class CommentResponse(
    val id: Long,
    val customerId: Long,
    val description: String,
    val createdAt: LocalDateTime,
    var replies: List<ReplyResponse>,
) {

    companion object {
        fun from(comment: CommunityComment, replies: List<CommunityReply>): CommentResponse {

            return CommentResponse(
                id = comment.id!!,
                customerId = comment.consumer.id!!,
                description = comment.description,
                createdAt = comment.createdAt,
                replies = replies.map { ReplyResponse.from(it) },
            )
        }
    }
}