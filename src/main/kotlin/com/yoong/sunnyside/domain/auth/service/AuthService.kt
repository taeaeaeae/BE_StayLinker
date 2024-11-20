package com.yoong.sunnyside.domain.auth.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.CustomIllegalArgumentException
import com.yoong.sunnyside.domain.auth.dto.*
import com.yoong.sunnyside.domain.business.entity.TempBusiness
import com.yoong.sunnyside.domain.business.repository.TempBusinessRepository
import com.yoong.sunnyside.domain.consumer.entity.TempConsumer
import com.yoong.sunnyside.domain.consumer.repository.ConsumerRepository
import com.yoong.sunnyside.domain.consumer.repository.TempConsumerJpaRepository
import com.yoong.sunnyside.infra.email.EmailUtils
import com.yoong.sunnyside.infra.redis.RedisUtils
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
    @Value("\${spring.mail.auth-code-expiration-millis}") private val expirationMillis: Long
){

    fun checkNickname(nickNameRequest: NicknameRequest): DefaultResponse {
        TODO()
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

    fun getRole(accessTokenRequest: AccessTokenRequest): MemberRoleResponse {
        TODO()
    }

    fun createMember(verifyCodeRequest: VerifyCodeRequest): EmailResponse {

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


}