package com.yoong.sunnyside.domain.real_estate.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.real_estate.dto.CreateRealEstateDto
import com.yoong.sunnyside.domain.real_estate.dto.RealEstateResponseDto
import com.yoong.sunnyside.domain.real_estate.dto.UpdateRealEstateDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class RealEstateServiceImpl: RealEstateService {

    override fun createRealEstate(createRealEstateDto: CreateRealEstateDto): DefaultResponseDto {
        TODO("Not yet implemented")
    }

    override fun getRealEstate(realEstateId: Long): RealEstateResponseDto {
        TODO("Not yet implemented")
    }

    override fun getRealEstateList(pageable: Pageable): Page<RealEstateResponseDto> {
        TODO("Not yet implemented")
    }

    override fun updateRealEstate(realEstateId: Long, updateRealEstateDto: UpdateRealEstateDto): DefaultResponseDto {
        TODO("Not yet implemented")
    }

    override fun deleteRealEstate(realEstateId: Long): DefaultResponseDto {
        TODO("Not yet implemented")
    }
}