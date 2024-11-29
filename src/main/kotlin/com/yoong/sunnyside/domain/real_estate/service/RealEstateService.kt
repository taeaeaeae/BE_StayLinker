package com.yoong.sunnyside.domain.real_estate.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.real_estate.dto.CreateRealEstate
import com.yoong.sunnyside.domain.real_estate.dto.RealEstatePageResponse
import com.yoong.sunnyside.domain.real_estate.dto.RealEstateResponse
import com.yoong.sunnyside.domain.real_estate.dto.UpdateRealEstate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface RealEstateService {

    fun createRealEstate(createRealEstate: CreateRealEstate): DefaultResponse

    fun getRealEstate(realEstateId: Long): RealEstateResponse

    fun getRealEstatePage(pageable: Pageable): Page<RealEstatePageResponse>

    fun updateRealEstate(realEstateId: Long, updateRealEstate: UpdateRealEstate): DefaultResponse

    fun deleteRealEstate(realEstateId: Long): DefaultResponse
}