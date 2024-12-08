package com.yoong.sunnyside.domain.faq.dto

data class CreateFaqRequest(
    val question: String,
    val answer: String,
    val division: String
)
