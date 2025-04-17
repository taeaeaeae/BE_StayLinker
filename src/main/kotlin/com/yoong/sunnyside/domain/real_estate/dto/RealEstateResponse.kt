package com.yoong.sunnyside.domain.real_estate.dto

import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import com.yoong.sunnyside.domain.real_estate.enum_class.GoodsType
import com.yoong.sunnyside.domain.real_estate.enum_class.HouseType
import com.yoong.sunnyside.domain.real_estate.enum_class.RealEstateAttributeData
import com.yoong.sunnyside.domain.real_estate.real_estate_attribute.entity.RealEstateAttribute
import com.yoong.sunnyside.domain.real_estate_option.dto.RealEstateOptionResponse
import com.yoong.sunnyside.domain.real_estate_option.entity.RealEstateOption
import java.time.LocalDateTime

data class RealEstateResponse (
    val id: Long,
    val businessId: Long, // 사업자 정보를 따로 받아 온다면 제거 예정
    val name: String,
    val description: String,
    val rate: Double,
    val price: Long,
    val latitude: Double,
    val longitude: Double,
    val attributes: Map<RealEstateAttributeData, Any>,
    val options: List<RealEstateOptionResponse>
){
    companion object {
        fun from(realEstate: RealEstate, attributes: Map<RealEstateAttributeData, Any>, options: List<RealEstateOption>): RealEstateResponse {
            return RealEstateResponse(
                id = realEstate.id!!,
                businessId = realEstate.businessId!!,
                name = realEstate.name,
                description = realEstate.description,
                rate = realEstate.rate,
                price = realEstate.price,
                latitude = realEstate.latitude,
                longitude = realEstate.longitude,
                attributes = attributes,
                options = options.map { RealEstateOptionResponse.from(it) }
            )
        }
    }
}