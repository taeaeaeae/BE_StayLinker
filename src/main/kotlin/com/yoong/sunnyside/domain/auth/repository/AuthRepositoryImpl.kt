package com.yoong.sunnyside.domain.auth.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.yoong.sunnyside.domain.business.entity.QBusiness
import com.yoong.sunnyside.domain.consumer.entity.QConsumer
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Repository

@Repository
class AuthRepositoryImpl(
    @PersistenceContext
    private val em: EntityManager,
) : AuthRepository {

    val queryFactory = JPAQueryFactory(em)
    val consumer = QConsumer.consumer!!
    val business = QBusiness.business!!

    override fun validNickname(nickname: String): Boolean {

        return checkedNickname(nickname)
    }

    private fun checkedNickname(nickname: String): Boolean {

        return queryFactory.selectOne()
            .from(consumer)
            .where(consumer.nickname.eq(nickname))
            .fetchFirst() != null
                || queryFactory.selectOne()
            .from(business)
            .where(business.nickName.eq(nickname))
            .fetchFirst() != null;
    }
}