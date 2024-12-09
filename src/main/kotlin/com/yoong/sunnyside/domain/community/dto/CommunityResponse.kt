package com.yoong.sunnyside.domain.community.dto

import com.querydsl.core.annotations.QueryProjection
import com.yoong.sunnyside.domain.community.comment.dto.CommentResponse
import com.yoong.sunnyside.domain.community.comment.entity.CommunityComment
import com.yoong.sunnyside.domain.community.comment.entity.CommunityReply
import com.yoong.sunnyside.domain.community.entity.Community
import java.time.LocalDateTime

data class CommunityResponse(
    val userId: Long,
    val postId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val title: String,
    val description: String,
    val image: List<String>,
    val replies: List<CommentResponse>
){
    companion object {
        fun from(community: Community, comments: List<CommunityComment>, replies: List<CommunityReply>): CommunityResponse {
            return CommunityResponse(
                userId = community.consumerId,
                postId = community.id!!,
                createdAt = community.createdAt,
                updatedAt = community.updatedAt,
                title = community.title,
                description = community.description,
                image = listOf(),
                replies = comments.map { CommentResponse.from(it, replies) }
            )
        }
    }
}