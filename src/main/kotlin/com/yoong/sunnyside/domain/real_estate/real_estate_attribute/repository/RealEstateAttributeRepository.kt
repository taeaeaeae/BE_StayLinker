package com.yoong.sunnyside.domain.real_estate.real_estate_attribute.repository

import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import com.yoong.sunnyside.domain.real_estate.enum_class.RealEstateAttributeData
import com.yoong.sunnyside.domain.real_estate.real_estate_attribute.entity.RealEstateAttribute
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface RealEstateAttributeRepository {
    fun save(realEstateAttribute: RealEstateAttribute): RealEstateAttribute
    fun findByAttributeData(realEstateId: Long): Map<RealEstateAttributeData, Any>
    fun findByRealEstateId(realEstateId: Long): List<RealEstateAttribute>
    fun delete(realEstateAttribute: RealEstateAttribute)
}