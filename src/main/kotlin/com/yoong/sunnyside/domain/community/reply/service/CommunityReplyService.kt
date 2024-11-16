package com.yoong.sunnyside.domain.community.reply.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.reply.dto.ReplyRequest
import com.yoong.sunnyside.domain.reply.service.ReplyService
import org.springframework.stereotype.Service

@Service
class CommunityReplyService: ReplyService {

    override fun createReply(replyRequest: ReplyRequest, id: Long): DefaultResponse {
        TODO("Not yet implemented")
    }

    override fun updateReply(replyRequest: ReplyRequest, id: Long): DefaultResponse {
        TODO("Not yet implemented")
    }

    override fun deleteReply(id: Long): DefaultResponse {
        TODO("Not yet implemented")
    }

    override fun reportReply(id: Long): DefaultResponse {
        TODO("Not yet implemented")
    }
}