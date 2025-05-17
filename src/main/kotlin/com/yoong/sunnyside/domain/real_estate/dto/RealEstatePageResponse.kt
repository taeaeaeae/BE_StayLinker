package com.yoong.sunnyside.domain.real_estate.dto

import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import com.yoong.sunnyside.domain.real_estate.enum_class.GoodsType
import com.yoong.sunnyside.domain.real_estate.enum_class.HouseType
import com.yoong.sunnyside.domain.real_estate.enum_class.RealEstateAttributeData

data class RealEstatePageResponse (
    val id: Long,
    val businessId: Long, // 사업자 정보를 따로 받아 온다면 제거 예정
    val name: String,
    val address: String,
    val price: Long,
    val attributes: Map<RealEstateAttributeData, Any>

){
    companion object {
        fun from(realEstate: RealEstate, realEstateAttribute: Map<RealEstateAttributeData, Any>): RealEstatePageResponse {
            return RealEstatePageResponse(
                id = realEstate.id!!,
                businessId = realEstate.businessId!!,
                name = realEstate.name,
                address = realEstate.address,
                price = realEstate.price,
                attributes = realEstateAttribute
            )
        }
    }
}