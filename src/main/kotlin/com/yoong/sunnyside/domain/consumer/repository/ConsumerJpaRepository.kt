package com.yoong.sunnyside.domain.consumer.repository

import com.yoong.sunnyside.domain.consumer.entity.Consumer
import org.springframework.data.jpa.repository.JpaRepository

interface ConsumerJpaRepository: JpaRepository<Consumer, Long> {

    fun findByEmail(email: String): Consumer?
}