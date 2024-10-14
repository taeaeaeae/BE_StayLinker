package com.yoong.sunnyside.domain.real_estate_option.repository.real_estate_to_option

import com.yoong.sunnyside.domain.real_estate_option.entity.RealEstateToOption
import org.springframework.stereotype.Repository

@Repository
class RealEstateToOptionRepositoryImpl(
    private val realEstateToOptionJpaRepository: RealEstateToOptionJpaRepository
): RealEstateToOptionRepository {
}