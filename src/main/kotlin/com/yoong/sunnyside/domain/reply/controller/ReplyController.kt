package com.yoong.sunnyside.domain.reply.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.reply.dto.ReplyRequest
import com.yoong.sunnyside.infra.security.MemberPrincipal
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

interface ReplyController {

    fun createReply(@PathVariable id: Long, @RequestBody replyRequest: ReplyRequest, @AuthenticationPrincipal principal: MemberPrincipal): ResponseEntity<DefaultResponse>
    fun updateReply(@PathVariable id: Long, @RequestBody replyRequest: ReplyRequest, @AuthenticationPrincipal principal: MemberPrincipal): ResponseEntity<DefaultResponse>
    fun deleteReply(@PathVariable id: Long, @AuthenticationPrincipal principal: MemberPrincipal): ResponseEntity<DefaultResponse>
    fun reportReply(@PathVariable id: Long, @AuthenticationPrincipal principal: MemberPrincipal): ResponseEntity<DefaultResponse>
}