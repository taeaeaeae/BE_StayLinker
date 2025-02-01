package com.yoong.sunnyside.domain.business.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class BusinessSignupRequest(
    val businessCode: String,
    val businessName: String,
    val phoneNumber: String,
    @field: NotBlank(message = "cannot be blank")
    @field: Pattern(
        regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$",
        message = "Please follow the email format")
    val email: String,
    @field: Min(value = 6, message = "cannot be blank")
    @field: Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{6,}\$",
        message = "Please make sure your password is at least 6 characters long, combining all upper and lower case letters, numbers, and special characters.")
    val password: String,
    val address: String,
    val businessCertificate: String,
    val nickName: String,
)