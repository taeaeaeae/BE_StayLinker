package com.yoong.sunnyside.domain.business.service

import com.yoong.sunnyside.domain.business.dto.BusinessSignupRequest
import com.yoong.sunnyside.domain.business.dto.LoginResponse
import com.yoong.sunnyside.domain.business.dto.LoginRequest
import com.yoong.sunnyside.domain.business.model.TempBusiness
import com.yoong.sunnyside.domain.business.repository.BusinessRepository
import com.yoong.sunnyside.domain.business.repository.TempBusinessRepository
import com.yoong.sunnyside.infra.security.MemberRole
import com.yoong.sunnyside.infra.security.jwt.JwtHelper
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BusinessService(
    private val businessRepository: BusinessRepository,
    private val tempBusinessRepository: TempBusinessRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtHelper: JwtHelper
) {

    @Transactional
    fun signUp(request: BusinessSignupRequest) {
        if (businessRepository.existsByBusinessCode(request.businessCode))
            throw IllegalArgumentException("business code ${request.businessCode} already exists")
        else if (tempBusinessRepository.existsByBusinessCode(request.businessCode))
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

    fun login(request: LoginRequest): LoginResponse {
        val business = businessRepository.findByBusinessCode(request.businessCode)
            ?: throw IllegalArgumentException("business code ${request.businessCode} not exists")
        if (!passwordEncoder.matches(request.password, business.password))
            throw IllegalArgumentException("password does not match.")

        return LoginResponse(
            accessToken = jwtHelper.generateToken(
                userId = business.id!!,
                role = MemberRole.BUSINESS
            )
        )
    }

    fun checkCode(code: String): Boolean {
        return (businessRepository.existsByBusinessCode(code)
                || tempBusinessRepository.existsByBusinessCode(code))
    }

    fun checkNickName(nickName: String): Boolean {
        return (businessRepository.existsByNickName(nickName)
                || tempBusinessRepository.existsByNickName(nickName))
    }

    fun checkEmail(email: String): Boolean {
        return (businessRepository.existsByEmail(email)
                || tempBusinessRepository.existsByEmail(email))
    }


}