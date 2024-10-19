package com.yoong.sunnyside.domain.business.service

import com.yoong.sunnyside.domain.business.dto.BusinessSignupRequest
import com.yoong.sunnyside.domain.business.model.Business
import com.yoong.sunnyside.domain.business.model.TempBusiness
import com.yoong.sunnyside.domain.business.repository.BusinessRepository
import com.yoong.sunnyside.domain.business.repository.TempBusinessRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BusinessService(
    private val businessRepository: BusinessRepository,
    private val tempBusinessRepository: TempBusinessRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun signUp(request: BusinessSignupRequest) {
        if (businessRepository.existsBusinessCode(request.businessCode))
            throw IllegalArgumentException("business code ${request.businessCode} already exists")
        else if (tempBusinessRepository.existsBusinessCode(request.businessCode))
            throw IllegalArgumentException("business code ${request.businessCode} under review")

        tempBusinessRepository.save(
            TempBusiness.from(
                businessCode = request.businessCode,
                businessName = request.businessName,
                phoneNumber = request.phoneNumber,
                email = request.email,
                password = passwordEncoder.encode(request.password),
                address = request.address,
                businessCertificate = request.businessCertificate,
                nickName = request.nickName,
            )
        )
    }


}