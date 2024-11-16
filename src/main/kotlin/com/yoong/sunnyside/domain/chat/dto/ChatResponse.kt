package com.yoong.sunnyside.domain.chat.dto

import java.time.LocalDateTime

data class ChatResponse(
    val roomId: Long,
    val toId: Long,
    val fromId: Long,
    val isSay: Boolean,
    val messageType: String,
    val msg: String,
    val msgTime: LocalDateTime,
)
