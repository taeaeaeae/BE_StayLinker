package com.yoong.sunnyside.domain.community.comment.repository

import com.yoong.sunnyside.domain.community.comment.entity.CommunityComment
import org.springframework.data.jpa.repository.JpaRepository

interface CommunityCommentJpaRepository: JpaRepository<CommunityComment, Long> {

    fun findByIdAndConsumerId(id: Long, consumerId: Long): CommunityComment?
    fun findByCommunityId(communityId: Long): List<CommunityComment>
}