package com.yoong.sunnyside.domain.real_estate_option.repository.real_estate_option

import com.yoong.sunnyside.domain.real_estate_option.entity.RealEstateOption
import org.springframework.data.jpa.repository.JpaRepository

interface RealEstateOptionJpaRepository: JpaRepository<RealEstateOption, Long> {
}