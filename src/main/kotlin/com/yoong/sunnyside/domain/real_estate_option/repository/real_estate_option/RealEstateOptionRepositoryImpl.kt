package com.yoong.sunnyside.domain.real_estate_option.repository.real_estate_option

import org.springframework.stereotype.Repository

@Repository
class RealEstateOptionRepositoryImpl(
    private val realEstateOptionJpaRepository: RealEstateOptionJpaRepository
): RealEstateOptionRepository {
}