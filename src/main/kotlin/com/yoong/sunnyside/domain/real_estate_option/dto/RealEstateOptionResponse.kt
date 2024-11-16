package com.yoong.sunnyside.domain.real_estate_option.dto

import com.yoong.sunnyside.domain.real_estate_option.entity.RealEstateOption

data class RealEstateOptionResponse(
    val name: String,
    val description: String?,
) {
    companion object {
        fun from(realEstateOption: RealEstateOption):RealEstateOptionResponse {
            return RealEstateOptionResponse(
                name = realEstateOption.name,
                description = realEstateOption.description
            )
        }
    }
}