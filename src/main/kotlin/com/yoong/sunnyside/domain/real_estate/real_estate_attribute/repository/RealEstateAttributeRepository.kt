package com.yoong.sunnyside.domain.real_estate.real_estate_attribute.repository

import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import com.yoong.sunnyside.domain.real_estate.real_estate_attribute.entity.RealEstateAttribute
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface RealEstateAttributeRepository {
    fun existsByAddress(address: String): Boolean
    fun save(realEstateAttribute: RealEstateAttribute): RealEstateAttribute
}