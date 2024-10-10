package com.yoong.sunnyside.domain.real_estate.repository

import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class RealEstateRepositoryImpl(
    private val realEstateJpaRepository: RealEstateJpaRepository
): RealEstateRepository {

    override fun existsByAddress(address: String): Boolean {
        return realEstateJpaRepository.existsByAddress(address)
    }

    override fun save(realEstate: RealEstate): RealEstate {
        return realEstateJpaRepository.save(realEstate)
    }

    override fun findByIdOrNull(realEstateId: Long): RealEstate? {
        return realEstateJpaRepository.findByIdOrNull(realEstateId)
    }
}