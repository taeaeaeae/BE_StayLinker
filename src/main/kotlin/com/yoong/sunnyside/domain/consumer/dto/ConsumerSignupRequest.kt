package com.yoong.sunnyside.domain.consumer.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class ConsumerSignupRequest(
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
    val confirmPassword: String,
    val nickname: String,
    val address: String,
    val phoneNumber: String,
    val languages: List<String>,
    val country: String,
    @field: Pattern(
        regexp = "^(?:(?:19|20)\\d{2})-(?:(?:0[13578]|1[02])-(?:0[1-9]|[12]\\d|3[01])|(?:0[469]|11)-(?:0[1-9]|[12]\\d|30)|02-(?:0[1-9]|1\\d|2[0-8]))\$|^(?:(?:19|20)(?:[02468][048]|[13579][26]))-02-29\$",
        message = "The birthday format does not match"
    )
    @NotBlank(message = "cannot be blank")
    val birthDay: String,
    @field: Min(value = 2, message = "cannot be blank")
    val name: String
)