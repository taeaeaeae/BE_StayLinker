package com.yoong.sunnyside.domain.real_estate_option.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.real_estate_option.dto.CreateRealEstateList
import com.yoong.sunnyside.domain.real_estate_option.dto.DeleteRealEstateOption

interface RealEstateOptionService {
    fun updateRealEstateOption(realEstateId: Long, createRealEstateList: CreateRealEstateList): DefaultResponse
    fun deleteRealEstateOption(realEstateId: Long, deleteRealEstateOption: DeleteRealEstateOption): DefaultResponse
}