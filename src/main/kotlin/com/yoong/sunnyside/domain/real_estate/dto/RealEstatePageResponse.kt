package com.yoong.sunnyside.domain.real_estate.dto

import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import com.yoong.sunnyside.domain.real_estate.enum_class.GoodsType
import com.yoong.sunnyside.domain.real_estate.enum_class.HouseType

data class RealEstatePageResponse (
    val id: Long,
    val businessId: Long, // 사업자 정보를 따로 받아 온다면 제거 예정
    val name: String,
    val address: String,
    val houseType: HouseType,
    val goodsType: GoodsType,
    val security: Int,
    val rent : Int,
    val maintenanceCost : Int,

){
    companion object {
        fun from(realEstate: RealEstate): RealEstatePageResponse {
            return RealEstatePageResponse(
                id = realEstate.id!!,
                businessId = realEstate.businessId!!,
                name = realEstate.name,
                address = realEstate.address,
                houseType = realEstate.houseType,
                goodsType = realEstate.goodsType,
                security = realEstate.security,
                rent = realEstate.rent,
                maintenanceCost = realEstate.maintenanceCost,
            )
        }
    }
}