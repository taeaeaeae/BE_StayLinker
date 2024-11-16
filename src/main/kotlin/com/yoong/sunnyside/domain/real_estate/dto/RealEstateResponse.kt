package com.yoong.sunnyside.domain.real_estate.dto

import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import com.yoong.sunnyside.domain.real_estate.enum_class.GoodsType
import com.yoong.sunnyside.domain.real_estate.enum_class.HouseType
import com.yoong.sunnyside.domain.real_estate_option.dto.RealEstateOptionResponse
import com.yoong.sunnyside.domain.real_estate_option.entity.RealEstateOption
import java.time.LocalDateTime

data class RealEstateResponse (
    val id: Long,
    val businessId: Long, // 사업자 정보를 따로 받아 온다면 제거 예정
    val name: String,
    val address: String,
    val completionDate: LocalDateTime,
    val houseType: HouseType,
    val goodsType: GoodsType,
    val security: Int,
    val rent : Int,
    val size: Double,
    val maintenanceCost : Int,
    val roomCount: Int,
    val floor: Int,
    val contractPeriod: LocalDateTime,
    val description: String,
    val bathroomCount: Int,
    val isParked: Boolean,
    val rate: Double,
    val options: List<RealEstateOptionResponse>
){
    companion object {
        fun from(realEstate: RealEstate, options: List<RealEstateOption>): RealEstateResponse {
            return RealEstateResponse(
                id = realEstate.id!!,
                businessId = realEstate.businessId!!,
                name = realEstate.name,
                address = realEstate.address,
                completionDate = realEstate.completionDate,
                houseType = realEstate.houseType,
                goodsType = realEstate.goodsType,
                security = realEstate.security,
                rent = realEstate.rent,
                size = realEstate.houseSize,
                maintenanceCost = realEstate.maintenanceCost,
                roomCount = realEstate.roomCount,
                floor = realEstate.floor,
                contractPeriod = realEstate.contractPeriod,
                description = realEstate.description,
                bathroomCount = realEstate.bathroomCount,
                isParked = realEstate.isParked,
                rate = realEstate.rate,
                options = options.map { RealEstateOptionResponse.from(it) }
            )
        }
    }
}