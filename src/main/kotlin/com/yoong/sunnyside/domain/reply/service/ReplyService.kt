package com.yoong.sunnyside.domain.reply.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.reply.dto.ReplyRequest

interface ReplyService {

    fun createReply(replyRequest: ReplyRequest, id: Long, authorId: Long): DefaultResponse
    fun updateReply(replyRequest: ReplyRequest, id: Long, authorId: Long): DefaultResponse
    fun deleteReply(id: Long, authorId: Long): DefaultResponse
    fun reportReply(id: Long, authorId: Long): DefaultResponse
}