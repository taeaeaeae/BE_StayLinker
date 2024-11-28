package com.yoong.sunnyside.domain.community.comment.repository

import com.yoong.sunnyside.domain.community.comment.entity.CommunityComment
import com.yoong.sunnyside.domain.community.comment.entity.CommunityReply
import org.springframework.data.jpa.repository.JpaRepository

interface CommunityReplyJpaRepository: JpaRepository<CommunityReply, Long> {

    fun findByIdAndConsumerId(id: Long, consumerId: Long): CommunityReply?
    fun findByCommentId(commentId: Long): List<CommunityReply>
    fun findAllByCommentId(id: Long): List<CommunityReply>
}