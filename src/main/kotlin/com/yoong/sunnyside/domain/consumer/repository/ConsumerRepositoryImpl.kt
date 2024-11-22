package com.yoong.sunnyside.domain.consumer.repository

import com.yoong.sunnyside.domain.consumer.entity.Consumer
import com.yoong.sunnyside.domain.consumer.entity.TempConsumer
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class ConsumerRepositoryImpl(
    private val consumerJpaRepository: ConsumerJpaRepository,
    private val tempConsumerJpaRepository: TempConsumerJpaRepository
): ConsumerRepository {

    override fun findByIdOrNull(id: Long): Consumer? {
        return consumerJpaRepository.findByIdOrNull(id)
    }

    override fun findByEmail(email: String): Consumer? {
        return consumerJpaRepository.findByEmail(email)
    }

    override fun tempUserSave(tempConsumer: TempConsumer) {
        tempConsumerJpaRepository.save(tempConsumer)
    }

    override fun save(consumer: Consumer):Consumer {
        return consumerJpaRepository.save(consumer)
    }

    override fun existsByNickname(nickname: String): Boolean {
        return consumerJpaRepository.existsByNickname(nickname)
    }
}