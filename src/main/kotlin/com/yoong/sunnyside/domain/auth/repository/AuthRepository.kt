package com.yoong.sunnyside.domain.auth.repository

interface AuthRepository {

    fun validNickname(nickname: String): Boolean
}