package com.yoong.sunnyside.infra.encrypt

interface Encrypt {

    fun encrypt(content: String): String
    fun decrypt(content: String): String
}