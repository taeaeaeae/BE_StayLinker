package com.yoong.sunnyside.domain.real_estate.repository

import org.springframework.stereotype.Repository

@Repository
class RealEstateRepositoryImpl(
    private val realEstateJpaRepository: RealEstateJpaRepository
): RealEstateRepository {
}