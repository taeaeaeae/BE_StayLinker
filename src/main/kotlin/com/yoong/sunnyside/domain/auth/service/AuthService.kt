package com.yoong.sunnyside.domain.auth.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.CustomIllegalArgumentException
import com.yoong.sunnyside.domain.admin.service.AdminService
import com.yoong.sunnyside.domain.auth.dto.*
import com.yoong.sunnyside.domain.auth.repository.AuthRepository
import com.yoong.sunnyside.domain.business.service.BusinessService
import com.yoong.sunnyside.domain.consumer.service.ConsumerService
import com.yoong.sunnyside.infra.email.EmailUtils
import com.yoong.sunnyside.infra.redis.RedisUtils
import com.yoong.sunnyside.infra.security.MemberPrincipal
import com.yoong.sunnyside.infra.security.MemberRole
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

const val AUTHENTICATE = "AUTHENTICATE"

@Service
class AuthService(
    private val emailUtils: EmailUtils,
    private val redisUtils: RedisUtils,
    private val authRepository: AuthRepository,
    @Value("\${spring.mail.auth-code-expiration-millis}") private val expirationMillis: Long,
    private val businessService: BusinessService,
    private val consumerService: ConsumerService,
    private val adminService: AdminService
){

    fun checkNickname(nickname: String): NicknameResponse {

        if(!validNickname(nickname)) throw CustomIllegalArgumentException("There is a duplicate nickname")

        return NicknameResponse(true)
    }



    fun sendEmail(emailRequest: EmailRequest): DefaultResponse {

        val title = "StayLinker Mail Authentication"

        val code = emailUtils.sendEmail(emailRequest.email, title)

        redisUtils.setStringData("${AUTHENTICATE}_${emailRequest.email}", code, expirationMillis)

        return DefaultResponse("Email send Successful")
    }

    fun verifyEmail(verifyCodeRequest: VerifyCodeRequest): DefaultResponse {

        val verityCode = redisUtils.getStringData("${AUTHENTICATE}_${verifyCodeRequest.email}")

        if(verityCode != verifyCodeRequest.code) throw CustomIllegalArgumentException("Verification code does not match")

        redisUtils.deleteStringData("${AUTHENTICATE}_${verifyCodeRequest.email}")

        redisUtils.setStringData(verifyCodeRequest.email, verifyCodeRequest.role.name, 300000)

        return DefaultResponse("Email sent successfully")

    }

    fun getRole(principal: MemberPrincipal): MemberRoleResponse {

        return MemberRoleResponse.from(principal)
    }

    @Transactional
    fun forgotPassword(forgotPasswordRequest: ForgotPasswordRequest):DefaultResponse{

        val verityCode = redisUtils.getStringData("${AUTHENTICATE}_${forgotPasswordRequest.email}")

        if(verityCode != forgotPasswordRequest.verificationCode) throw CustomIllegalArgumentException("Verification code does not match")

        if(forgotPasswordRequest.newPassword != forgotPasswordRequest.retypeNewPassword) throw CustomIllegalArgumentException("password does not match")

        when(forgotPasswordRequest.role){
            MemberRole.BUSINESS -> businessService.changePassword(forgotPasswordRequest.newPassword, forgotPasswordRequest.email)
            MemberRole.CONSUMER -> consumerService.forgotPassword(forgotPasswordRequest.newPassword, forgotPasswordRequest.email)
            MemberRole.ADMIN -> adminService.forgotPassword(forgotPasswordRequest.newPassword, forgotPasswordRequest.email)
        }

        return DefaultResponse("Password changed successfully")
    }

    private fun validNickname(nickname: String): Boolean {

        if (authRepository.validNickname(nickname)) throw CustomIllegalArgumentException("There is a duplicate nickname")

        return true
    }



}