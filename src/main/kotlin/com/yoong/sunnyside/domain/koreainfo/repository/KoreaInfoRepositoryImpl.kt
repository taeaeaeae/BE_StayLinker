package com.yoong.sunnyside.domain.koreainfo.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Expression
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.EntityPathBase
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.core.types.dsl.PathBuilder
import com.querydsl.jpa.impl.JPAQueryFactory
import com.yoong.sunnyside.common.enum_class.SearchType
import com.yoong.sunnyside.domain.koreainfo.entity.KoreaInfo
import com.yoong.sunnyside.domain.koreainfo.entity.QKoreaInfo
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository

@Repository
class KoreaInfoRepositoryImpl(
    @PersistenceContext
    private val em: EntityManager
) : KoreaInfoQuerydslRepository {
    private val queryFactory = JPAQueryFactory(em)
    private val koreaInfo = QKoreaInfo.koreaInfo
    override fun findPage(
        pageable: Pageable,
        division: String?,
        searchType: SearchType?,
        keyword: String?
    ): Page<KoreaInfo> {
        val builder = BooleanBuilder()
        division?.let { builder.and(koreaInfo.division.eq(it)) }

        if (searchType == SearchType.TITLE) {
            keyword?.let { builder.and(koreaInfo.title.`in`(it)) }
        } else if (searchType == SearchType.CONTENT) {
            keyword?.let { builder.and(koreaInfo.content.`in`(it)) }
        }

        val totalCount = queryFactory.select(koreaInfo.count())
            .from(koreaInfo)
            .fetchOne() ?: 0L

        val contents = queryFactory.selectFrom(koreaInfo)
            .orderBy(*orderSpecifiers(pageable.sort, koreaInfo))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .where(builder)
            .fetch()

        return PageImpl(contents, pageable, totalCount)
    }

    private fun orderSpecifiers(sort: Sort, root: EntityPathBase<*>): Array<OrderSpecifier<*>> {
//        val orders = arrayListOf<OrderSpecifier<*>>()
//        sort.forEach {
//            orders.add(
//                OrderSpecifier(
//                    if (it.isAscending) Order.ASC else Order.DESC,
//                    PathBuilder(root.type, root.metadata)
//                        .get(it.property) as Expression<out Comparable<*>>
//                )
//            )
//        }
//
//        return orders.toTypedArray()
        return sort.map { order ->
            val property = Expressions.path(Comparable::class.java, root, order.property)
            if (order.isAscending) {
                OrderSpecifier(Order.ASC, property)
            } else {
                OrderSpecifier(Order.DESC, property)
            }
        }.toList().toTypedArray()
    }

}