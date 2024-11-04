package com.yoong.sunnyside.domain.reply.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.reply.dto.ReplyRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

interface ReplyController {

    fun createReply(@PathVariable id: Long, @RequestBody replyRequestDto: ReplyRequestDto): ResponseEntity<DefaultResponseDto>
    fun updateReply(@PathVariable id: Long, @RequestBody replyRequestDto: ReplyRequestDto): ResponseEntity<DefaultResponseDto>
    fun deleteReply(@PathVariable id: Long): ResponseEntity<DefaultResponseDto>
}