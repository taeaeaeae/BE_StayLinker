package com.yoong.sunnyside.domain.real_estate.dto

import com.yoong.sunnyside.domain.real_estate.enum_class.GoodsType
import com.yoong.sunnyside.domain.real_estate.enum_class.HouseType
import java.time.LocalDateTime

data class RealEstateResponseDto (
    val id: Long,
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
)