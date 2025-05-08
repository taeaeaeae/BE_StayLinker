package com.yoong.sunnyside.domain.consumer.repository

import com.yoong.sunnyside.domain.consumer.entity.Consumer

interface ConsumerRepository {

    fun findByIdOrNull(id: Long): Consumer?
    fun findByEmail(email: String): Consumer?
    fun save(consumer: Consumer): Consumer
    fun existsByNickname(nickname: String): Boolean
}