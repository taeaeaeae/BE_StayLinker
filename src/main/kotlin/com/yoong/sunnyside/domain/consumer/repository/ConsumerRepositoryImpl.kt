package com.yoong.sunnyside.domain.consumer.repository

import org.springframework.stereotype.Repository

@Repository
class ConsumerRepositoryImpl(
    private val consumerJpaRepository: ConsumerJpaRepository,
    private val tempConsumerJpaRepository: TempConsumerJpaRepository
): ConsumerRepository {
}