package com.yoong.sunnyside.domain.reply.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.reply.dto.ReplyRequest

interface ReplyService {

    fun createReply(replyRequest: ReplyRequest, id: Long): DefaultResponse
    fun updateReply(replyRequest: ReplyRequest, id: Long): DefaultResponse
    fun deleteReply(id: Long): DefaultResponse
    fun reportReply(id: Long): DefaultResponse
}