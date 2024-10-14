package com.yoong.sunnyside.domain.real_estate_option.repository.real_estate_to_option

import com.yoong.sunnyside.domain.real_estate_option.entity.RealEstateToOption
import org.springframework.data.jpa.repository.JpaRepository

interface RealEstateToOptionJpaRepository: JpaRepository<RealEstateToOption, Long> {
}