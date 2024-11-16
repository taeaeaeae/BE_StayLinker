package com.yoong.sunnyside.domain.chat.dto

data class ChatRequest(
    val roomId: Long,
    val toId: Long,
    val fromId: Long,
    val isSay: Boolean,
    val messageType: String,
    val msg: String,
)
