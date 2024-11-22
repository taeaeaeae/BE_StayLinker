package com.yoong.sunnyside.domain.auth.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.CustomIllegalArgumentException
import com.yoong.sunnyside.domain.auth.dto.*
import com.yoong.sunnyside.domain.auth.repository.AuthRepository
import com.yoong.sunnyside.domain.business.entity.TempBusiness
import com.yoong.sunnyside.domain.business.repository.TempBusinessRepository
import com.yoong.sunnyside.domain.consumer.entity.TempConsumer
import com.yoong.sunnyside.domain.consumer.repository.TempConsumerJpaRepository
import com.yoong.sunnyside.infra.email.EmailUtils
import com.yoong.sunnyside.infra.redis.RedisUtils
import com.yoong.sunnyside.infra.security.MemberPrincipal
import com.yoong.sunnyside.infra.security.MemberRole
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

const val AUTHENTICATE = "AUTHENTICATE"

@Service
class AuthService(
    private val emailUtils: EmailUtils,
    private val redisUtils: RedisUtils,
    private val tempConsumerRepository: TempConsumerJpaRepository,
    private val tempBusinessRepository: TempBusinessRepository,
    private val authRepository: AuthRepository,
    @Value("\${spring.mail.auth-code-expiration-millis}") private val expirationMillis: Long
){

    fun checkNickname(nickname: String): NicknameResponse {

        if(!validNickname(nickname)) throw CustomIllegalArgumentException("There is a duplicate nickname")

        return NicknameResponse(true)
    }



    fun sendEmail(emailRequest: EmailRequest): DefaultResponse {

        val code: String = emailUtils.createCode()
        val title = "StayLinker Mail Authentication"

        emailUtils.sendEmail(emailRequest.email, title, code)

        redisUtils.setStringData("${AUTHENTICATE}_${emailRequest.email}", code, expirationMillis)

        return DefaultResponse("Email send Successful")
    }

    fun verifyEmail(verifyCodeRequest: VerifyCodeRequest): EmailResponse {

        val verityCode = redisUtils.getStringData("${AUTHENTICATE}_${verifyCodeRequest.email}")

        if(verityCode == verifyCodeRequest.code){

            redisUtils.deleteStringData("${AUTHENTICATE}_${verifyCodeRequest.email}")

            return createMember(verifyCodeRequest)
        }

        throw CustomIllegalArgumentException("Verification code does not match")
    }

    fun getRole(principal: MemberPrincipal): MemberRoleResponse {

        return MemberRoleResponse.from(principal)
    }

    private fun createMember(verifyCodeRequest: VerifyCodeRequest): EmailResponse {

        if(verifyCodeRequest.role == MemberRole.CONSUMER){

            val consumer = tempConsumerRepository.saveAndFlush(
                TempConsumer(
                    email = verifyCodeRequest.email,
                    password = "",
                    address = "",
                    nickname = "",
                    country = "",
                    phoneNumber = "",
                    foreignNumber = null,
                    foreignCreateAt = null,
                    role = MemberRole.CONSUMER
                )
            )

            return EmailResponse(
                id = consumer.id!!,
                role = MemberRole.CONSUMER
            )

        }else{

            val business = tempBusinessRepository.saveAndFlush(
                TempBusiness(
                    email = verifyCodeRequest.email,
                    password = "",
                    address = "",
                    businessCode = "",
                    businessName = "",
                    businessCertificate = "",
                    nickName = "",
                    isApproved = false,
                    phoneNumber = "",
                )
            )

            return EmailResponse(
                id = business.id!!,
                role = MemberRole.CONSUMER
            )

        }

    }

    private fun validNickname(nickname: String): Boolean {

        if (authRepository.validNickname(nickname)) throw CustomIllegalArgumentException("There is a duplicate nickname")

        return true
    }



}