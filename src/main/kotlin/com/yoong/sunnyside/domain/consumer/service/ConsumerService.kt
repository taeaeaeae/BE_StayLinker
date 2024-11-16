package com.yoong.sunnyside.domain.consumer.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.CustomIllegalArgumentException
import com.yoong.sunnyside.common.exception.ModelNotFoundException
import com.yoong.sunnyside.domain.business.dto.LoginResponse
import com.yoong.sunnyside.domain.consumer.dto.ConsumerLoginRequest
import com.yoong.sunnyside.domain.consumer.dto.ConsumerSignupRequest
import com.yoong.sunnyside.domain.consumer.dto.ConsumerUpdateRequest
import com.yoong.sunnyside.domain.consumer.dto.PasswordRequest
import com.yoong.sunnyside.domain.consumer.entity.TempConsumer
import com.yoong.sunnyside.domain.consumer.repository.ConsumerRepository
import com.yoong.sunnyside.infra.security.config.PasswordEncoderConfig
import com.yoong.sunnyside.infra.security.jwt.JwtHelper
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ConsumerService(
    private val consumerRepository: ConsumerRepository,
    private val passwordEncoderConfig: PasswordEncoderConfig,
    private val jwtHelper: JwtHelper
){

    private val passwordEncoder = passwordEncoderConfig.passwordEncoder()

    @Transactional
    fun signUp(request : ConsumerSignupRequest): DefaultResponse{

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
}