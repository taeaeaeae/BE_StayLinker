package com.yoong.sunnyside.domain.reply.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.reply.dto.ReplyRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

interface ReplyService {

    fun createReply(replyRequestDto: ReplyRequestDto, id: Long): DefaultResponseDto
    fun updateReply(replyRequestDto: ReplyRequestDto, id: Long): DefaultResponseDto
    fun deleteReply(id: Long): DefaultResponseDto
}