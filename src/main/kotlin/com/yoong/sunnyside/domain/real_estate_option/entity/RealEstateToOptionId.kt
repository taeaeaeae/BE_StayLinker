package com.yoong.sunnyside.domain.real_estate_option.entity

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class RealEstateToOptionId(

    val realEstateId: Long = 0,

    val optionId: Long = 0

): Serializable