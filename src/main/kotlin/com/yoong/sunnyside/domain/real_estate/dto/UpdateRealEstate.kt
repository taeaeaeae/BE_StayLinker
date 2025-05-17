package com.yoong.sunnyside.domain.real_estate.dto

import com.yoong.sunnyside.domain.real_estate.enum_class.GoodsType
import com.yoong.sunnyside.domain.real_estate.enum_class.HouseType
import com.yoong.sunnyside.domain.real_estate.enum_class.RealEstateAttributeData
import com.yoong.sunnyside.domain.real_estate_option.dto.CreateRealEstateOption
import java.time.LocalDateTime

data class UpdateRealEstate (
    var name: String,
    var price: Long,
    var description: String,
    var address: String,
    var latitude: Double,
    var longitude: Double,
    var attributes: Map<RealEstateAttributeData, Any>
)