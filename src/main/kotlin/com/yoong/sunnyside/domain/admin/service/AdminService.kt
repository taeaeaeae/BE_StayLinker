package com.yoong.sunnyside.domain.admin.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.AccessDeniedException
import com.yoong.sunnyside.domain.admin.dto.AdminSignupRequest
import com.yoong.sunnyside.domain.admin.repository.AdminRepository
import com.yoong.sunnyside.domain.business.dto.BusinessResponse
import com.yoong.sunnyside.domain.admin.dto.AdminLoginRequest
import com.yoong.sunnyside.domain.admin.dto.LoginResponse
import com.yoong.sunnyside.domain.admin.entity.Admin
import com.yoong.sunnyside.domain.admin.enum_class.AdminStatus
import com.yoong.sunnyside.domain.business.repository.BusinessRepository
import com.yoong.sunnyside.infra.security.config.PasswordEncoderConfig
import com.yoong.sunnyside.infra.security.jwt.JwtHelper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminService(
    private val businessRepository: BusinessRepository,
    private val adminRepository: AdminRepository,
    private val passwordEncoderConfig: PasswordEncoderConfig,
    private val jwtHelper: JwtHelper
) {

    val passwordEncoder = passwordEncoderConfig.passwordEncoder()

    fun login(request: AdminLoginRequest): LoginResponse {
        val admin = adminRepository.findByEmail(request.email)
            ?: throw IllegalArgumentException("email ${request.email} not exists")
        if (!passwordEncoder.matches(request.password, admin.password))
            throw IllegalArgumentException("password does not match.")
        else if (admin.status == AdminStatus.DEACTIVATE) throw AccessDeniedException("비활성화된 계정입니다.")

        return LoginResponse(
            accessToken = jwtHelper.generateToken(
                userId = admin.id!!,
                role = admin.role
            )
        )
    }

    @Transactional
    fun signUp(request: AdminSignupRequest): DefaultResponse {
        if (adminRepository.existsByEmail(request.email))
            throw IllegalArgumentException("email ${request.email} already exists")

        adminRepository.save(
            Admin.from(request.email, passwordEncoder.encode(request.password))
        )

        return DefaultResponse("가입신청이 완료되었습니다.")
    }

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