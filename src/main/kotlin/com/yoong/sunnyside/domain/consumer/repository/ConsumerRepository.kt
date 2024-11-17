package com.yoong.sunnyside.domain.consumer.repository

import com.yoong.sunnyside.domain.consumer.entity.Consumer
import com.yoong.sunnyside.domain.consumer.entity.TempConsumer

interface ConsumerRepository {

    fun findByIdOrNull(id: Long): Consumer?
    fun findByEmail(email: String): Consumer?
    fun tempUserSave(tempConsumer: TempConsumer)
    fun save(consumer: Consumer): Consumer
}