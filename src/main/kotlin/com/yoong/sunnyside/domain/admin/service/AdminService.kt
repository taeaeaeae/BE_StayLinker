package com.yoong.sunnyside.domain.admin.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.admin.repository.AdminRepository
import com.yoong.sunnyside.domain.business.dto.BusinessResponse
import com.yoong.sunnyside.domain.business.dto.BusinessSignupRequest
import com.yoong.sunnyside.domain.business.entity.Business
import com.yoong.sunnyside.domain.business.repository.BusinessRepository
import com.yoong.sunnyside.infra.security.config.PasswordEncoderConfig
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminService(
    private val businessRepository: BusinessRepository,
    private val adminRepository: AdminRepository,
    private val passwordEncoderConfig: PasswordEncoderConfig
) {

    val passwordEncoder = passwordEncoderConfig.passwordEncoder()

    @Transactional
    fun allowBusiness(id: Long): DefaultResponse {
        val business = businessRepository.findByIdOrNull(id)
            ?: throw IllegalArgumentException("id $id under review")

        if (businessRepository.existsByBusinessCode(business.businessCode))
            throw IllegalArgumentException("business code ${business.businessCode} already exists")

        business.allowBusiness()

        return DefaultResponse("allow business")
    }

    fun getAllApplication(): List<BusinessResponse> {
        return businessRepository.findAll().map { BusinessResponse.from(it) }
    }

    fun getAllBusiness(): List<BusinessResponse> {
        return businessRepository.findAll().map { BusinessResponse.from(it) }
    }

    fun forgotPassword(newPassword: String, email: String) {
        val admin = adminRepository.findByEmail(email) ?: throw RuntimeException("email not found")

        admin.changePassword(passwordEncoder.encode(newPassword))
    }

}