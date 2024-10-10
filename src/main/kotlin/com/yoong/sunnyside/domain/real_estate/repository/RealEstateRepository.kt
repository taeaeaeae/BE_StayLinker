package com.yoong.sunnyside.domain.real_estate.repository

import com.yoong.sunnyside.domain.real_estate.entity.RealEstate

interface RealEstateRepository {
    fun existsByAddress(address: String): Boolean
    fun save(realEstate: RealEstate): RealEstate
    fun findByIdOrNull(realEstateId: Long): RealEstate?
}