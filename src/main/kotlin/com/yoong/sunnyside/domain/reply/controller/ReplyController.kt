package com.yoong.sunnyside.domain.reply.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.reply.dto.ReplyRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

interface ReplyController {

    fun createReply(@PathVariable id: Long, @RequestBody replyRequest: ReplyRequest): ResponseEntity<DefaultResponse>
    fun updateReply(@PathVariable id: Long, @RequestBody replyRequest: ReplyRequest): ResponseEntity<DefaultResponse>
    fun deleteReply(@PathVariable id: Long): ResponseEntity<DefaultResponse>
    fun reportReply(@PathVariable id: Long): ResponseEntity<DefaultResponse>
}