package com.yoong.sunnyside.domain.real_estate.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.real_estate.dto.CreateRealEstate
import com.yoong.sunnyside.domain.real_estate.dto.RealEstatePageResponse
import com.yoong.sunnyside.domain.real_estate.dto.RealEstateResponse
import com.yoong.sunnyside.domain.real_estate.dto.UpdateRealEstate
import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import com.yoong.sunnyside.domain.real_estate.repository.RealEstateRepository
import com.yoong.sunnyside.domain.real_estate_option.entity.RealEstateOption
import com.yoong.sunnyside.domain.real_estate_option.repository.RealEstateOptionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RealEstateServiceImpl(
    private val realEstateRepository: RealEstateRepository,
    private val realEstateOptionRepository: RealEstateOptionRepository
): RealEstateService {

    @Transactional
    override fun createRealEstate(createRealEstate: CreateRealEstate): DefaultResponse {

        // 협의 필요
        if(realEstateRepository.existsByAddress(createRealEstate.address)) throw RuntimeException("중복 되는 매물 입니다")

        val realEstate = realEstateRepository.saveAndFlush(
            //business 테이블 이 없는 관계로 우선 1L 로 설정
            RealEstate(1L, createRealEstate)
        )

        realEstateOptionRepository.saveAll(createRealEstate.options.map { RealEstateOption(it, realEstate) })

        return DefaultResponse("매물 생성이 완료 되었습니다")
    }

    override fun getRealEstate(realEstateId: Long): RealEstateResponse {

        val realEstate = realEstateRepository.findByIdOrNull(realEstateId) ?: throw RuntimeException("해당 매물이 존재 하지 않습니다")

        val options = realEstateOptionRepository.findAllByRealEstateId(realEstateId)

        return RealEstateResponse.from(realEstate, options)
    }

    override fun getRealEstatePage(pageable: Pageable): Page<RealEstatePageResponse> {

        val realEstatePage = realEstateRepository.findAll(pageable)

        return realEstatePage.map { RealEstatePageResponse.from(it) }
    }

    @Transactional
    override fun updateRealEstate(realEstateId: Long, updateRealEstate: UpdateRealEstate): DefaultResponse {

        val realEstate = realEstateRepository.findByIdOrNull(realEstateId) ?: throw RuntimeException("해당 매물이 존재 하지 않습니다")

        realEstate.update(updateRealEstate)

        return DefaultResponse("매물 수정이 완료 되었습니다")

    }

    @Transactional
    override fun deleteRealEstate(realEstateId: Long): DefaultResponse {

        val realEstate = realEstateRepository.findByIdOrNull(realEstateId) ?: throw RuntimeException("해당 매물이 존재 하지 않습니다")

        realEstate.delete()

        return DefaultResponse("매물 삭제가 완료 되었습니다")
    }
}