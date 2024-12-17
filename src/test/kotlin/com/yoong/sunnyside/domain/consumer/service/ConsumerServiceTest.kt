package com.yoong.sunnyside.domain.consumer.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.CustomIllegalArgumentException
import com.yoong.sunnyside.domain.consumer.dto.ConsumerLoginRequest
import com.yoong.sunnyside.domain.consumer.dto.ConsumerSignupRequest
import com.yoong.sunnyside.domain.consumer.dto.ConsumerUpdateRequest
import com.yoong.sunnyside.domain.consumer.dto.PasswordRequest
import com.yoong.sunnyside.domain.consumer.entity.Consumer
import com.yoong.sunnyside.domain.consumer.entity.TempConsumer
import com.yoong.sunnyside.domain.consumer.repository.ConsumerRepository
import com.yoong.sunnyside.infra.redis.RedisUtils
import com.yoong.sunnyside.infra.security.MemberRole
import com.yoong.sunnyside.infra.security.config.PasswordEncoderConfig
import com.yoong.sunnyside.infra.security.jwt.JwtHelper
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime

class ConsumerServiceTest : StringSpec({

    val consumerRepository = mockk<ConsumerRepository>()
    val passwordEncoderConfig = mockk<PasswordEncoderConfig>()
    val jwtHelper = mockk<JwtHelper>()
    val passwordEncoder = mockk<PasswordEncoder>()
    val redisUtils = mockk<RedisUtils>()
    lateinit var consumerService: ConsumerService

    beforeTest {
        every { passwordEncoderConfig.passwordEncoder() } returns passwordEncoder
        every { passwordEncoder.encode(any()) } returns "password"

        consumerService = ConsumerService(consumerRepository, passwordEncoderConfig, jwtHelper, redisUtils)
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
        every { consumerRepository.tempUserSave(any()) } answers {

        }

        //Then
        val result = consumerService.signUp(consumerSignupRequest)

        result shouldBe DefaultResponse("login successful")
    }

    "새로운 패스워드와 패스워드 확인이 일치하지 않을 경우 CustomIllegalArgumentException"{
        //Given
        val passwordRequest = PasswordRequest(
            password = "test",
            newPassword = "test",
            retryPassword = "test2",
        )

        //When & Then
        shouldThrow<CustomIllegalArgumentException> {
            consumerService.changePassword(passwordRequest, 1L)
        }.let {
            it.message shouldBe "Password does not match"
        }

    }

    "DB 데이터 상 비밀번호와 이전 비밀번호가 일치하지 않을 경우 CustomIllegalArgumentException"{
        //Given
        val passwordRequest = PasswordRequest(
            password = "test",
            newPassword = "test2",
            retryPassword = "test2",
        )

        val consumer = Consumer(
            email = "test@test.com",
            password = "test2",
            nickname = "test2",
            address = "test",
            phoneNumber = "test",
            country = "test",
            foreignNumber = "001",
            foreignCreateAt = "2020-01-01",
            role = MemberRole.CONSUMER
        )

        consumer.apply {
            id = 1L
        }


        //When & Then

        every { consumerRepository.findByIdOrNull(any()) } returns consumer
        every { passwordEncoder.matches(any(), any()) } returns false
        shouldThrow<CustomIllegalArgumentException> {
            consumerService.changePassword(passwordRequest, consumer.id!!)
        }.let {
            it.message shouldBe "Password does not match"
        }

    }

    "새로운 비밀번호와 이전 비밀번호가 일치할 경우 CustomIllegalArgumentException"{
        //Given
        val passwordRequest = PasswordRequest(
            password = "test",
            newPassword = "test",
            retryPassword = "test",
        )

        val consumer = Consumer(
            email = "test@test.com",
            password = "test",
            nickname = "test2",
            address = "test",
            phoneNumber = "test",
            country = "test",
            foreignNumber = "001",
            foreignCreateAt = "2020-01-01",
            role = MemberRole.CONSUMER
        )

        consumer.apply {
            id = 1L
        }


        //When & Then

        every { consumerRepository.findByIdOrNull(any()) } returns consumer
        every { passwordEncoder.matches(any(), any()) } returns true
        shouldThrow<CustomIllegalArgumentException> {
            consumerService.changePassword(passwordRequest, consumer.id!!)
        }.let {
            it.message shouldBe "It's the same password"
        }

    }

    "개인 정보 업데이트 정상 동작"{

        //Given
        val consumerUpdateRequest = ConsumerUpdateRequest(
            nickname = "test3",
            address = "seoul",
            languages = listOf(),
        )

        val consumer = Consumer(
            email = "test@test.com",
            password = "test2",
            nickname = "test2",
            address = "test",
            phoneNumber = "test",
            country = "test",
            foreignNumber = "001",
            foreignCreateAt = "2020-01-01",
            role = MemberRole.CONSUMER
        )

        //When
        every { consumerRepository.findByIdOrNull(any()) } returns consumer

        //Given
        val result = consumerService.updateConsumer(consumerUpdateRequest, 1L)

        result shouldBe DefaultResponse("update consumer successful")
        consumer.address shouldBe "seoul"
        consumer.nickname shouldBe "test3"
    }

    "개인 정보 삭제 정상 동작"{

        //Given

        val consumer = Consumer(
            email = "test@test.com",
            password = "test2",
            nickname = "test2",
            address = "test",
            phoneNumber = "test",
            country = "test",
            foreignNumber = "001",
            foreignCreateAt = "2020-01-01",
            role = MemberRole.CONSUMER
        )

        //When
        every { consumerRepository.findByIdOrNull(any()) } returns consumer

        //Given
        val result = consumerService.deleteConsumer(1L)

        result shouldBe DefaultResponse("deleted consumer successful")
        consumer.deletedAt shouldNotBe null
    }
})
