package com.yoong.sunnyside.domain.consumer.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.AccessDeniedException
import com.yoong.sunnyside.common.exception.CustomIllegalArgumentException
import com.yoong.sunnyside.common.exception.ModelNotFoundException
import com.yoong.sunnyside.domain.business.dto.LoginResponse
import com.yoong.sunnyside.domain.consumer.dto.*
import com.yoong.sunnyside.domain.consumer.entity.Consumer
import com.yoong.sunnyside.domain.consumer.entity.TempConsumer
import com.yoong.sunnyside.domain.consumer.repository.ConsumerRepository
import com.yoong.sunnyside.domain.consumer.repository.TempConsumerJpaRepository
import com.yoong.sunnyside.infra.encrypt.utils.AESUtil
import com.yoong.sunnyside.infra.redis.RedisUtils
import com.yoong.sunnyside.infra.security.MemberPrincipal
import com.yoong.sunnyside.infra.security.config.PasswordEncoderConfig
import com.yoong.sunnyside.infra.security.jwt.JwtHelper
import com.yoong.sunnyside.infra.web_client.hikorea.HiKoreaClient
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ConsumerService(
    private val consumerRepository: ConsumerRepository,
    private val passwordEncoderConfig: PasswordEncoderConfig,
    private val jwtHelper: JwtHelper,
    private val redisUtils: RedisUtils,
    private val hiKoreaClient: HiKoreaClient,
    private val aesUtil: AESUtil
){

    private val passwordEncoder = passwordEncoderConfig.passwordEncoder()

    val log = LoggerFactory.getLogger("ConsumerService")

    @Transactional
    fun signUp(request : ConsumerSignupRequest): DefaultResponse{

        redisUtils.getStringData(request.email) ?: throw ModelNotFoundException("이메일 인증이 진행 되지 않았습니다")

        if(request.password != request.confirmPassword) throw CustomIllegalArgumentException("Password does not match")
        consumerRepository.tempUserSave(TempConsumer(request, passwordEncoder.encode(request.password)))

        return DefaultResponse("login successful")
    }

    fun login(consumerLoginRequest: ConsumerLoginRequest): LoginResponse {

        val consumer = consumerRepository.findByEmail(consumerLoginRequest.email)
            ?: throw ModelNotFoundException("${consumerLoginRequest.email} not found")
        if(!passwordEncoder.matches(consumerLoginRequest.password, consumer.password)) throw CustomIllegalArgumentException("Password does not match")

        return LoginResponse(jwtHelper.generateToken(consumer.id!!, consumer.role))
    }

    @Transactional
    fun changePassword(passwordRequest: PasswordRequest, id: Long): DefaultResponse {

        if(passwordRequest.newPassword != passwordRequest.retryPassword) throw CustomIllegalArgumentException("Password does not match")
        val consumer = consumerRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("Member is not found")
        if(!passwordEncoder.matches(passwordRequest.password, consumer.password)) throw CustomIllegalArgumentException("Password does not match")
        if (passwordEncoder.matches(passwordRequest.newPassword, consumer.password)) throw CustomIllegalArgumentException("It's the same password")
        consumer.changePassword(passwordEncoder.encode(passwordRequest.newPassword))

        return DefaultResponse("change password successful")
    }

    @Transactional
    fun updateConsumer(consumerUpdateRequest: ConsumerUpdateRequest, id: Long): DefaultResponse {

        val consumer = consumerRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("member is not found")

        consumer.update(consumerUpdateRequest)

        return DefaultResponse("update consumer successful")
    }

    @Transactional
    fun deleteConsumer(id: Long): DefaultResponse {

        val consumer = consumerRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("member is not found")

        consumer.delete()

        return DefaultResponse("deleted consumer successful")
    }

    @Transactional
    fun forgotPassword(password: String, email: String) {

        val consumer = consumerRepository.findByEmail(email) ?: throw ModelNotFoundException("member is not found")

        consumer.changePassword(passwordEncoder.encode(password))
    }

    @Transactional
    fun verifyAlienRegistrationCardByString(alienRegistrationCardRequest: AlienRegistrationCardRequest, id: Long): DefaultResponse{

        val apiResult = hiKoreaClient.request(alienRegistrationCardRequest)

        log.info(apiResult.toString())

        val apiData = (apiResult["data"] as Map<*, *>).mapNotNull{

            if (it.key is String && it.value is String) it.key as String to it.value as String else null
        }.toMap()

        log.info(apiData.toString())

        if(apiData["REGCHECKYN"].toString() != "Y") throw AccessDeniedException("외국인 등록 정보가 일치하지 않습니다")

        val tempConsumer = consumerRepository.tempUserFindByIdOrNull(id) ?: throw ModelNotFoundException("해당 유저가 존재하지 않습니다")

        consumerRepository.save(Consumer(alienRegistrationCardRequest, tempConsumer))

        return DefaultResponse(apiData["REGCHECKYN"].toString())

    }

    fun verifyAlienRegistrationCardByImage(principal: MemberPrincipal, alienRegistrationCardRequest: AlienRegistrationCardRequest): DefaultResponse{
        TODO()
    }

}