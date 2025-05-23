package com.yoong.sunnyside.domain.business.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.CustomIllegalArgumentException
import com.yoong.sunnyside.common.exception.ModelNotFoundException
import com.yoong.sunnyside.common.exception.ValidException
import com.yoong.sunnyside.domain.business.dto.*
import com.yoong.sunnyside.domain.business.dto.BusinessSignupRequest
import com.yoong.sunnyside.common.dto.LoginResponse
import com.yoong.sunnyside.domain.business.dto.LoginRequest
import com.yoong.sunnyside.domain.business.dto.PasswordChangeRequest
import com.yoong.sunnyside.domain.business.entity.Business
import com.yoong.sunnyside.domain.business.enum_class.BusinessSearchType
import com.yoong.sunnyside.domain.business.repository.BusinessRepository
import com.yoong.sunnyside.infra.openApi.BusinessVerification
import com.yoong.sunnyside.infra.security.jwt.JwtHelper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class BusinessService(
    private val businessRepository: BusinessRepository,
    private val passwordEncoder: PasswordEncoder,
    private val businessVerification: BusinessVerification,
    private val jwtHelper: JwtHelper
) {

    @Transactional
    fun signUp(request: BusinessSignupRequest): DefaultResponse {
        if (businessRepository.existsByBusinessCode(request.businessCode))
            throw IllegalArgumentException("business code ${request.businessCode} already exists")

        val business = businessVerification.getOfficeInfo(request.registrationCode)
        if (business.brkrNm != request.agentName) throw ValidException("대표자 이름이 일치하지 않습니다.")
        if (business.bsnmCmpnm != request.businessName) throw ValidException("사업자 상호명이 일치하지 않습니다.")
        if (business.registDe != request.registrationCode) throw ValidException("등록일자가 일치하지 않습니다.")

        businessRepository.save(
            Business.from(request, passwordEncoder.encode(request.password))
        )

        return DefaultResponse("가입 신청이 완료되었습니다.")
    }

    fun login(request: LoginRequest): LoginResponse {
        val business = businessRepository.findByBusinessCodeAndDeletedAtIsNull(request.businessCode)
            ?: throw IllegalArgumentException("business code ${request.businessCode} not exists")
        if (!passwordEncoder.matches(request.password, business.password))
            throw IllegalArgumentException("password does not match.")

        return LoginResponse(
            accessToken = jwtHelper.generateToken(
                userId = business.id!!,
                role = business.role
            )
        )
    }

    fun passwd(request: PasswordChangeRequest, id: Long) {
        val business =
            businessRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("가입되지 않는 사업자등록번호 입니다.")
        if (request.password == request.retryPassword) business.passwdChange(passwordEncoder.encode(request.password))
    }

    fun searchBusiness(page: Long, type: BusinessSearchType, keyword: String): List<BusinessSearchResultResponse> {
        val businessList = when (type) {
            BusinessSearchType.NAME -> businessVerification.searchNameResult(keyword)
            BusinessSearchType.AGENT_NAME -> businessVerification.searchAgentNameResult(keyword)
            BusinessSearchType.CERTIFICATE -> businessVerification.searchCertificateResult(keyword)
        }

        return businessList

    }

    fun businessInfo(request: BusinessVerifyRequest): BusinessVerifyResponse {
        if (businessRepository.existsByBusinessCode(request.businessCode)) throw CustomIllegalArgumentException("이미 가입된 사업자 등록번호 입니다.")

        val business = businessVerification.getOfficeInfo(request.registrationCode)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return BusinessVerifyResponse(
            request.businessCode,
            business.jurirno,
            business.brkrNm,
            business.bsnmCmpnm,
            business.registDe,
            business.mnnmadr,
            business.rdnmadr,
            LocalDateTime.parse(business.estbsBeginDe, formatter),
            LocalDateTime.parse(business.estbsEndDe, formatter)
        )
    }

    fun checkNickName(nickName: String): Boolean {
        return (businessRepository.existsByNickName(nickName))
    }

    fun checkEmail(email: String): Boolean {
        return (businessRepository.existsByEmail(email))
    }

    fun changePassword(password: String, email: String) {

        val business = businessRepository.findByEmail(email) ?: throw RuntimeException("email not found")

        business.passwdChange(passwordEncoder.encode(password))
    }

    fun myPage(id: Long): BusinessResponse {
        val business = businessRepository.findByIdOrNull(id) ?: throw RuntimeException("business id not found")

        return BusinessResponse.from(business)
    }

    fun memberModify(id: Long) {
        val business = businessRepository.findByIdOrNull(id) ?: throw RuntimeException("business id not found")

    }


}
