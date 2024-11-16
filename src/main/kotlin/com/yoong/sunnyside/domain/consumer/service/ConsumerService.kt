package com.yoong.sunnyside.domain.consumer.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.business.dto.LoginResponse
import com.yoong.sunnyside.domain.consumer.dto.ConsumerLoginRequest
import com.yoong.sunnyside.domain.consumer.dto.ConsumerSignupRequest
import com.yoong.sunnyside.domain.consumer.dto.ConsumerUpdateRequest
import com.yoong.sunnyside.domain.consumer.dto.PasswordRequest
import org.springframework.stereotype.Service

@Service
class ConsumerService{

    fun signUp(consumerSignupRequest : ConsumerSignupRequest): DefaultResponse{
        TODO()
    }

    fun login(consumerLoginRequest: ConsumerLoginRequest): LoginResponse {
        TODO()
    }

    fun changePassword(passwordRequest: PasswordRequest): DefaultResponse {
        TODO()
    }

    fun updateConsumer(consumerUpdateRequest: ConsumerUpdateRequest): DefaultResponse {
        TODO()
    }

    fun deleteConsumer(): DefaultResponse {
        TODO()
    }
}