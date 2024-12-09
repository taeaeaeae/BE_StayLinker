package com.yoong.sunnyside.domain.community.repository

import com.querydsl.core.Tuple
import com.querydsl.core.types.Projections
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import com.yoong.sunnyside.common.exception.ModelNotFoundException
import com.yoong.sunnyside.domain.community.comment.dto.CommentResponse
import com.yoong.sunnyside.domain.community.comment.dto.ReplyResponse
import com.yoong.sunnyside.domain.community.comment.entity.QCommunityComment
import com.yoong.sunnyside.domain.community.comment.entity.QCommunityReply
import com.yoong.sunnyside.domain.community.dto.CommunityResponse
import com.yoong.sunnyside.domain.community.entity.Community
import com.yoong.sunnyside.domain.community.entity.QCommunity
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityNotFoundException
import jakarta.persistence.PersistenceContext
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class CommunityRepositoryImpl(
    private val communityJpaRepository: CommunityJpaRepository,
    @PersistenceContext
    private val em: EntityManager
): CommunityRepository {

    val queryFactory = JPAQueryFactory(em)
    val community = QCommunity.community!!
    val communityComment = QCommunityComment.communityComment!!
    val communityReply = QCommunityReply.communityReply!!

    override fun save(community: Community): Community {
        return communityJpaRepository.save(community)
    }

    override fun findByIdOrNull(communityId: Long): Community? {

       return communityJpaRepository.findByIdOrNull(communityId)
    }

    override fun findById(communityId: Long): CommunityResponse {

        val communityResult = communityJpaRepository.findByIdOrNull(communityId) ?: throw ModelNotFoundException("Community is not found")

        val communityComments = queryFactory.selectFrom(
            communityComment
        )
            .where(communityComment.community.id.eq(communityId))
            .fetch()

        val communityCommentIds = communityComments.map { it.id }

        val communityReplies = queryFactory.selectFrom(communityReply)
            .where(communityReply.comment.id.`in`(communityCommentIds))
            .fetch()

        return CommunityResponse.from(communityResult, communityComments, communityReplies)
    }

    override fun findAll(cursor: Long?, limit: Int, search: String?): List<Community> {

        val safeLimit = if (limit in 1..1000) limit else 10

        val query = queryFactory
            .selectFrom(community)
            .where(
                cursor?.let{ community.id.lt(it) },
                search?.let {
                    community.title.like(it)
                        .or(community.description.like(it))
                           },
            )
            .orderBy(community.id.desc())
            .limit(safeLimit.toLong())
            .fetch()

        return query
    }

    override fun findByIdAndConsumerId(id: Long, consumerId: Long): Community? {
        return findByIdAndConsumerId(id, consumerId)
    }

    // 커서 기반 으로 페이지네이션을 할지 논의
    override fun findAllByIdIn(ids: List<Long>): List<Community> {
        return communityJpaRepository.findAllByIdIn(ids)
    }
}