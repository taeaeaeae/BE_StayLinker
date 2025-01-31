package com.yoong.sunnyside.domain.business.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.CustomIllegalArgumentException
import com.yoong.sunnyside.common.exception.ModelNotFoundException
import com.yoong.sunnyside.common.exception.ValidException
import com.yoong.sunnyside.domain.business.dto.*
import com.yoong.sunnyside.domain.business.entity.TempBusiness
import com.yoong.sunnyside.domain.business.repository.BusinessRepository
import com.yoong.sunnyside.domain.business.repository.TempBusinessRepository
import com.yoong.sunnyside.infra.openApi.BusinessVerification
import com.yoong.sunnyside.infra.openApi.BusinessVerifyResponse
import com.yoong.sunnyside.infra.security.MemberRole
import com.yoong.sunnyside.infra.security.jwt.JwtHelper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BusinessService(
    private val businessRepository: BusinessRepository,
    private val tempBusinessRepository: TempBusinessRepository,
    private val passwordEncoder: PasswordEncoder,
    private val businessVerification: BusinessVerification,
    private val jwtHelper: JwtHelper
) {

    @Transactional
    fun signUp(request: BusinessSignupRequest): DefaultResponse {
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
                openingDate = request.openingDate,
            )
        )
        return DefaultResponse("created")
    }

    fun login(request: LoginRequest): LoginResponse {
        val business = businessRepository.findByBusinessCodeAndDeletedAtIsNull(request.businessCode)
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

    fun passwd(request: PasswordChangeRequest, id: Long) {
        val business =
            businessRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("가입되지 않는 사업자등록번호(${id}) 입니다.")
        if (request.password == request.retryPassword) business.passwdChange(passwordEncoder.encode(request.password))
    }

    fun checkVerify(request: BusinessVerifyRequest): DefaultResponse? {

        if (businessRepository.existsByBusinessCode(request.businessNumber)) throw CustomIllegalArgumentException("이미 가입된 사업자 등록번호 입니다.")
        else if (tempBusinessRepository.existsByBusinessCode(request.businessNumber)) throw CustomIllegalArgumentException(
            "가입심사 진행중입니다."
        )
        val business = businessVerification.getOfficeInfo(request.registrationNumber)
        if (business.brkrNm != request.agentName) throw ValidException("대표자 이름이 일치하지 않습니다.")
        if (business.bsnmCmpnm != request.name) throw ValidException("사업자 상호명이 일치하지 않습니다.")
        if (business.registDe != request.registrationNumber) throw ValidException("등록일자가 일치하지 않습니다.")
        return DefaultResponse("인증되었습니다.")
    }

    fun checkNickName(nickName: String): Boolean {
        return (businessRepository.existsByNickName(nickName)
                || tempBusinessRepository.existsByNickName(nickName))
    }

    fun checkEmail(email: String): Boolean {
        return (businessRepository.existsByEmail(email)
                || tempBusinessRepository.existsByEmail(email))
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