package com.yoong.sunnyside.domain.consumer.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.business.dto.LoginResponse
import com.yoong.sunnyside.domain.consumer.dto.ConsumerLoginRequestDto
import com.yoong.sunnyside.domain.consumer.dto.ConsumerSignupRequestDto
import com.yoong.sunnyside.domain.consumer.dto.ConsumerUpdateRequestDto
import com.yoong.sunnyside.domain.consumer.dto.PasswordRequestDto
import org.springframework.stereotype.Service

@Service
class ConsumerService{

    fun signUp(consumerSignupRequestDto : ConsumerSignupRequestDto): DefaultResponseDto{
        TODO()
    }

    fun login(consumerLoginRequestDto: ConsumerLoginRequestDto): LoginResponse {
        TODO()
    }

    fun changePassword(passwordRequestDto: PasswordRequestDto): DefaultResponseDto {
        TODO()
    }

    fun updateConsumer(consumerUpdateRequestDto: ConsumerUpdateRequestDto): DefaultResponseDto {
        TODO()
    }

    fun deleteConsumer(): DefaultResponseDto {
        TODO()
    }
}