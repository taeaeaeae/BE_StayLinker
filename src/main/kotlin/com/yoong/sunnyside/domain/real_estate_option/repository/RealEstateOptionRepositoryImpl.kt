package com.yoong.sunnyside.domain.real_estate_option.repository

import com.yoong.sunnyside.domain.real_estate_option.entity.RealEstateOption
import org.springframework.stereotype.Repository

@Repository
class RealEstateOptionRepositoryImpl(
    private val realEstateOptionJpaRepository: RealEstateOptionJpaRepository
): RealEstateOptionRepository {

    override fun saveAll(options: List<RealEstateOption>) {
        realEstateOptionJpaRepository.saveAll(options)
    }

    override fun findAllByRealEstateId(realEstateId: Long): List<RealEstateOption> {
        return realEstateOptionJpaRepository.findAllByRealEstateId(realEstateId)
    }
}