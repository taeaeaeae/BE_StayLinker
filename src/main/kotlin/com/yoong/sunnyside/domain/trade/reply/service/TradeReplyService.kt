package com.yoong.sunnyside.domain.trade.reply.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.trade.reply.dto.TradeReplyRequestDto
import org.springframework.stereotype.Service

@Service
class TradeReplyService {

        fun createReply(tradeReplyRequestDto: TradeReplyRequestDto, communityId: Long): DefaultResponseDto {
            TODO()
        }

        fun updateReply(tradeReplyRequestDto: TradeReplyRequestDto, replyId: Long): DefaultResponseDto {
            TODO()
        }

        fun deleteReply(replyId: Long): DefaultResponseDto {
            TODO()
        }
}