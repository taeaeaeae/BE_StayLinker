package com.yoong.sunnyside.domain.community.repository

import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.ComparableExpressionBase
import com.querydsl.core.types.dsl.Expressions
import com.yoong.sunnyside.common.exception.CustomIllegalArgumentException
import org.springframework.data.domain.Sort
import java.time.LocalDateTime

fun Sort.Order.toOrderSpecifier(): OrderSpecifier<*> {
    val direction = if (isAscending) Order.ASC else Order.DESC
    val path = when (property) {
        "id" -> Expressions.numberPath(Long::class.java, "price")
        "createdAt" -> Expressions.dateTimePath(LocalDateTime::class.java, "createdAt")
        else -> throw CustomIllegalArgumentException("Unknown sort property: $property")
    }
    return OrderSpecifier(direction, path as ComparableExpressionBase<*>)
}