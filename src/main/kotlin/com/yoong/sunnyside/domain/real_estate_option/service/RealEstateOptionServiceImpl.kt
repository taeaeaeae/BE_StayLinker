package com.yoong.sunnyside.domain.real_estate_option.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.real_estate.repository.RealEstateRepository
import com.yoong.sunnyside.domain.real_estate_option.dto.CreateRealEstateList
import com.yoong.sunnyside.domain.real_estate_option.dto.DeleteRealEstateOption
import com.yoong.sunnyside.domain.real_estate_option.entity.RealEstateOption
import com.yoong.sunnyside.domain.real_estate_option.repository.RealEstateOptionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RealEstateOptionServiceImpl(
    private val realEstateOptionRepository: RealEstateOptionRepository,
    private val realEstateRepository: RealEstateRepository,
): RealEstateOptionService {

    @Transactional
    override fun updateRealEstateOption(realEstateId: Long, createRealEstateList: CreateRealEstateList): DefaultResponse {

        val realEstate = realEstateRepository.findByIdOrNull(realEstateId) ?: throw RuntimeException("매물 정보가 존재 하지 않습니다")

        realEstateOptionRepository.saveAll(createRealEstateList.options.map { RealEstateOption(it, realEstate) })

        return DefaultResponse("매물 옵션 수정이 완료 되었습니다")
    }

    @Transactional
    override fun deleteRealEstateOption(realEstateId: Long, deleteRealEstateOption: DeleteRealEstateOption): DefaultResponse {

        if(!realEstateRepository.existsById(realEstateId)) throw RuntimeException("매물 정보가 존재 하지 않습니다")

        val realEstateOptions = realEstateOptionRepository.findAllByIdAndRealEstateId(realEstateId, deleteRealEstateOption.optionIds)

        realEstateOptions.map {
            it.delete()
        }

        return DefaultResponse("매물 옵션 삭제가 완료 되었습니다")
    }
}