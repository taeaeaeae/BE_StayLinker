package com.yoong.sunnyside.domain.consumer.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.CustomIllegalArgumentException
import com.yoong.sunnyside.domain.consumer.dto.ConsumerLoginRequest
import com.yoong.sunnyside.domain.consumer.dto.ConsumerSignupRequest
import com.yoong.sunnyside.domain.consumer.entity.TempConsumer
import com.yoong.sunnyside.domain.consumer.repository.ConsumerRepository
import com.yoong.sunnyside.infra.security.config.PasswordEncoderConfig
import com.yoong.sunnyside.infra.security.jwt.JwtHelper
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

class ConsumerServiceTest : StringSpec({

    val consumerRepository = mockk<ConsumerRepository>()
    val passwordEncoderConfig = mockk<PasswordEncoderConfig>()
    val jwtHelper = mockk<JwtHelper>()
    val passwordEncoder = mockk<PasswordEncoder>()
    lateinit var consumerService: ConsumerService

    beforeTest {
        every { passwordEncoderConfig.passwordEncoder() } returns passwordEncoder
        every { passwordEncoder.encode(any()) } returns "password"

        consumerService = ConsumerService(consumerRepository, passwordEncoderConfig, jwtHelper)
    }

    "패스워드와 패스워드 확인이 일치하지 않을 경우 CustomIllegalArgumentException"{

        //Given

        val consumerSignupRequest = ConsumerSignupRequest(
            email = "test@test.com",
            password = "test",
            confirmPassword = "test2",
            nickname = "test2",
            address = "test",
            phoneNumber = "test",
            languages = listOf(),
            country = "test"
        )

        //When & Then
        shouldThrow<CustomIllegalArgumentException> {
            consumerService.signUp(consumerSignupRequest)
        }.let{
            it.message shouldBe "Password does not match"
        }
    }

    "회원가입 정상 동작"{

        //Given

        val consumerSignupRequest = ConsumerSignupRequest(
            email = "test@test.com",
            password = "test",
            confirmPassword = "test",
            nickname = "test2",
            address = "test",
            phoneNumber = "test",
            languages = listOf(),
            country = "test"
        )

        //When

        every { passwordEncoder.encode(any()) } returns "testXX"
        every { consumerRepository.tempUserSave(any()) }

        //Then
        val result = consumerService.signUp(consumerSignupRequest)

        result shouldBe DefaultResponse("login successful")
    }


})
