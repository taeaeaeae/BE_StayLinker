package com.yoong.sunnyside.domain.auth.dto

import jakarta.validation.constraints.Pattern

data class CheckNicknameDto(

    @field:Pattern(
        regexp = "^(?i)[a-z0-9]{4,15}$",
        message = "Nickname must be 4-15 characters long and contain only letters and numbers."
    )
    val nickname: String,
)