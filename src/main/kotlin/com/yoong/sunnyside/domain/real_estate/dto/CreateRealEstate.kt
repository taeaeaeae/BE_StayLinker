package com.yoong.sunnyside.domain.real_estate.dto

import com.yoong.sunnyside.domain.real_estate.enum_class.RealEstateAttributeData
import com.yoong.sunnyside.domain.real_estate_option.dto.CreateRealEstateOption

data class CreateRealEstate (
    var name: String,
    var price: Long,
    var description: String,
    var options: List<CreateRealEstateOption>,
    var latitude: Double,
    var longitude: Double,
    var attributes: Map<RealEstateAttributeData, Any>
)