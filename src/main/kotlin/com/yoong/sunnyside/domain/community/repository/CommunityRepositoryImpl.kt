package com.yoong.sunnyside.domain.community.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.yoong.sunnyside.common.exception.ModelNotFoundException
import com.yoong.sunnyside.domain.community.comment.entity.QCommunityComment
import com.yoong.sunnyside.domain.community.comment.entity.QCommunityReply
import com.yoong.sunnyside.domain.community.dto.CommunityResponse
import com.yoong.sunnyside.domain.community.entity.Community
import com.yoong.sunnyside.domain.community.entity.QCommunity
import com.yoong.sunnyside.domain.community.enum_class.CommunityType
import com.yoong.sunnyside.domain.community.enum_class.SetOrder
import com.yoong.sunnyside.domain.community.favorite.entity.QCommunityFavorite
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import com.querydsl.core.Tuple
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQuery
import com.yoong.sunnyside.domain.community.dto.CommunityProjectionDto
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Repository
class CommunityRepositoryImpl(
    private val communityJpaRepository: CommunityJpaRepository,
    @PersistenceContext
    private val em: EntityManager
): CommunityRepository {

    val log = LoggerFactory.getLogger("CommunityRepository")
    val queryFactory = JPAQueryFactory(em)
    val community = QCommunity.community!!
    val communityComment = QCommunityComment.communityComment!!
    val communityReply = QCommunityReply.communityReply!!
    val favorite = QCommunityFavorite.communityFavorite!!

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

    override fun findAll(cursor: String?, limit: Int, search: String?, communityType: CommunityType, setOrder: SetOrder): List<CommunityProjectionDto> {

        //sealed class 로 빼는게 나을 듯??
        val tempCursor:Any? = when {
            cursor?.toLongOrNull() != null -> cursor.toLong()
            kotlin.runCatching {
                LocalDateTime.parse(cursor!!, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            }.isSuccess ->  LocalDateTime.parse(cursor!!, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            else -> null
        }

        val likeCount = favorite.communityId.count().coalesce(0)


        val safeLimit = if (limit in 1..1000) limit else 10

        val query = queryFactory
            .select(
               Projections.constructor(
                   CommunityProjectionDto::class.java,
                   community,
                   likeCount
               )
            )
            .from(community)
            .leftJoin(favorite)
            .on(community.id.eq(favorite.communityId))
            .groupBy(community.id)
            .fetchJoin()


        if(communityType != CommunityType.ALL){
            query.where(
                community.communityType.eq(communityType)
            )
        }

        when (setOrder) {
            SetOrder.NEWEST -> {
                query.where(
                    cursor?.let{ community.createdAt.lt(tempCursor as LocalDateTime) },
                    search?.let {
                        community.title.like(it)
                            .or(community.description.like(it))
                    },
                )
                    .orderBy(community.createdAt.desc())
                    .limit(safeLimit.toLong())
            }
            SetOrder.OLDEST -> {
                query.where(
                    cursor?.let{ community.createdAt.lt(tempCursor as LocalDateTime) },
                    search?.let {
                        community.title.like(it)
                            .or(community.description.like(it))
                    },
                )
                    .orderBy(community.createdAt.asc())
                    .limit(safeLimit.toLong())
            }
            SetOrder.POPULAR -> {

                query.where(
                        cursor?.let { community.id.lt(tempCursor as Long) },
                        search?.let {
                            community.title.like(it)
                                .or(community.description.like(it))
                        },
                    )
                    .orderBy(likeCount.desc() , community.id.desc())
                    .limit(safeLimit.toLong())
            }
        }

        return query.fetch()
    }

    override fun findByIdAndConsumerId(id: Long, consumerId: Long): Community? {
        return findByIdAndConsumerId(id, consumerId)
    }

    // 커서 기반 으로 페이지네이션을 할지 논의
    override fun findAllByIdIn(ids: List<Long>): List<Community> {
        return communityJpaRepository.findAllByIdIn(ids)
    }

}