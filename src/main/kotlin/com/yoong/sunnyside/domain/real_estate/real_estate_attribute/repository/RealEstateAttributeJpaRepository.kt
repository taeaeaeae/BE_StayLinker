package com.yoong.sunnyside.domain.real_estate.real_estate_attribute.repository

import com.yoong.sunnyside.domain.real_estate.real_estate_attribute.entity.RealEstateAttribute
import org.springframework.data.jpa.repository.JpaRepository

interface RealEstateAttributeJpaRepository: JpaRepository<RealEstateAttribute, Long> {

}