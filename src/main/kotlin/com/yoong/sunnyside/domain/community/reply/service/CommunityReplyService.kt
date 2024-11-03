package com.yoong.sunnyside.domain.community.reply.service

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.community.reply.dto.CommunityReplyRequestDto
import org.springframework.stereotype.Service

@Service
class CommunityReplyService {

        fun createReply(communityReplyRequestDto: CommunityReplyRequestDto, communityId: Long): DefaultResponseDto {
            TODO()
        }

        fun updateReply(communityReplyRequestDto: CommunityReplyRequestDto, replyId: Long): DefaultResponseDto {
            TODO()
        }

        fun deleteReply(replyId: Long): DefaultResponseDto {
            TODO()
        }
}