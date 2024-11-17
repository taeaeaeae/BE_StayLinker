package com.yoong.sunnyside.domain.community.comment.repository

import org.springframework.stereotype.Repository

@Repository
class CommunityCommentRepositoryImpl(
    private val communityCommentJpaRepository: CommunityCommentJpaRepository
): CommunityCommentRepository {
}