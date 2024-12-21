package com.yoong.sunnyside.domain.notification.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import com.yoong.sunnyside.domain.notification.entity.Notification
import com.yoong.sunnyside.domain.notification.entity.QNotification
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class NotificationRepositoryImpl(
    @PersistenceContext
    private val em: EntityManager
) : NotificationQuerydslRepository {
    private val queryFactory = JPAQueryFactory(em)
    private val notification = QNotification.notification

    override fun findPage(
        pageSize: Long,
        cursor: Any?,
        division: String?,
        keyword: String?
    ): List<Notification> {

        val builder = BooleanBuilder()
        builder.and(notification.deletedAt.isNull())
        division?.let { builder.and(notification.division.eq(it)) }
        keyword?.let { builder.and(notification.title.contains(it)) }
        cursor?.let { builder.and(notification.id.lt(cursor as Long)) }

        return queryFactory.selectFrom(notification)
            .where(builder)
            .orderBy(notification.id.desc())
            .limit(pageSize)
            .fetch()
    }

}