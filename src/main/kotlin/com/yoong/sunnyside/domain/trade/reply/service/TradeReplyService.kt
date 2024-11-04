package com.yoong.sunnyside.domain.trade.reply.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.reply.dto.ReplyRequestDto
import com.yoong.sunnyside.domain.reply.service.ReplyService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
@Qualifier("tradeReplyService")
class TradeReplyService : ReplyService {

    override fun createReply(replyRequestDto: ReplyRequestDto, id: Long): DefaultResponseDto {
        TODO("Not yet implemented")
    }

    override fun updateReply(replyRequestDto: ReplyRequestDto, id: Long): DefaultResponseDto {
        TODO("Not yet implemented")
    }

    override fun deleteReply(id: Long): DefaultResponseDto {
        TODO("Not yet implemented")
    }
}