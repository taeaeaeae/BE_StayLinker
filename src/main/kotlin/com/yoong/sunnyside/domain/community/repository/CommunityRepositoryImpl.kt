package com.yoong.sunnyside.domain.community.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.yoong.sunnyside.domain.community.entity.Community
import com.yoong.sunnyside.domain.community.entity.QCommunity
import jakarta.persistence.EntityManager
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

    override fun save(community: Community): Community {
        return communityJpaRepository.save(community)
    }

    override fun findByIdOrNull(communityId: Long): Community? {
        return communityJpaRepository.findByIdOrNull(communityId)
    }

    override fun findAll(cursor: Long?, limit: Int, search: String?): List<Community> {

        val safeLimit = if (limit in 1..1000) limit else 10

        val query = queryFactory
            .selectFrom(community)
            .where(
                cursor?.let{ community.id.lt(it) },
                search.let {
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
}