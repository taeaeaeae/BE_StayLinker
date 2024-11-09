package com.yoong.sunnyside.domain.community.reply.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.reply.dto.ReplyRequestDto
import com.yoong.sunnyside.domain.reply.service.ReplyService
import org.springframework.stereotype.Service

@Service
class CommunityReplyService: ReplyService {

    override fun createReply(replyRequestDto: ReplyRequestDto, id: Long): DefaultResponseDto {
        TODO("Not yet implemented")
    }

    override fun updateReply(replyRequestDto: ReplyRequestDto, id: Long): DefaultResponseDto {
        TODO("Not yet implemented")
    }

    override fun deleteReply(id: Long): DefaultResponseDto {
        TODO("Not yet implemented")
    }

    override fun reportReply(id: Long): DefaultResponseDto {
        TODO("Not yet implemented")
    }
}