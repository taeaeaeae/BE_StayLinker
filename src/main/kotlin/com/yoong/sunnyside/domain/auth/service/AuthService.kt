package com.yoong.sunnyside.domain.auth.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.auth.dto.EmailRequestDto
import com.yoong.sunnyside.domain.auth.dto.NicknameRequestDto
import org.springframework.stereotype.Service

@Service
class AuthService {

    fun checkNickname(nickNameRequestDto: NicknameRequestDto): DefaultResponseDto {
        TODO()
    }

    fun sendEmail(emailRequestDto: EmailRequestDto): DefaultResponseDto {
        TODO()
    }

    fun verifyEmail(emailRequestDto: EmailRequestDto, code: String): EmailRequestDto {
        TODO()
    }


}