package com.yoong.sunnyside.domain.real_estate_option.entity

import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import jakarta.persistence.*

@IdClass(RealEstateToOptionId::class)
@Entity
@Table(name = "real_estate_to_option")
class RealEstateToOption(

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "real_estate_id")
    val realEstate: RealEstate,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    val option: RealEstateOption,
){
}