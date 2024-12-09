package com.yoong.sunnyside.domain.consumer.repository

import com.yoong.sunnyside.domain.consumer.entity.TempConsumer
import org.springframework.data.jpa.repository.JpaRepository

interface TempConsumerJpaRepository: JpaRepository<TempConsumer, Long> {
    fun existsByNickname(nickname: String): Boolean
}