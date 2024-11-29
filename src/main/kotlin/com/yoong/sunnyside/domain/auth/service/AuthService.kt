package com.yoong.sunnyside.domain.auth.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.auth.dto.AccessTokenRequest
import com.yoong.sunnyside.domain.auth.dto.EmailRequest
import com.yoong.sunnyside.domain.auth.dto.MemberRoleResponse
import com.yoong.sunnyside.domain.auth.dto.NicknameRequest
import org.springframework.stereotype.Service

@Service
class AuthService {

    fun checkNickname(nickNameRequest: NicknameRequest): DefaultResponse {
        TODO()
    }

    fun sendEmail(emailRequest: EmailRequest): DefaultResponse {
        TODO()
    }

    fun verifyEmail(emailRequest: EmailRequest, code: String): EmailRequest {
        TODO()
    }

    fun getRole(accessTokenRequest: AccessTokenRequest): MemberRoleResponse {
        TODO()
    }


}