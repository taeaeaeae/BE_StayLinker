package com.yoong.sunnyside.common.dto

data class ErrorResponse(
    val errorCode: Int,
    val errorMessage: String
)