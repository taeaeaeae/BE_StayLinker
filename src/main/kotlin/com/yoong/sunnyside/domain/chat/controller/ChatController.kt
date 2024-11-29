package com.yoong.sunnyside.domain.chat.controller

import com.yoong.sunnyside.domain.chat.dto.ChatRequest
import com.yoong.sunnyside.domain.chat.dto.ChatResponse
import com.yoong.sunnyside.domain.chat.service.ChatService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "채팅 관련 API", description = "정확하게 정해지지 않은 API 입니다")
@RestController
@RequestMapping("/chat")
class ChatController(
    private val chatService: ChatService
){

    @Operation(summary = "채팅 API", description = "확정 되지 않은 API 입니다")
    @PostMapping
    fun chat(
        @RequestBody chatRequest: ChatRequest
    ): ResponseEntity<ChatResponse>
        = ResponseEntity.status(HttpStatus.OK).body(chatService.chat(chatRequest))
}