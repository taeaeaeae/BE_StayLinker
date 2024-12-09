package com.yoong.sunnyside.domain.community.comment.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.ModelNotFoundException
import com.yoong.sunnyside.domain.community.comment.entity.CommunityComment
import com.yoong.sunnyside.domain.community.comment.entity.CommunityReply
import com.yoong.sunnyside.domain.community.comment.repository.CommunityCommentJpaRepository
import com.yoong.sunnyside.domain.community.comment.repository.CommunityReplyJpaRepository
import com.yoong.sunnyside.domain.community.repository.CommunityRepository
import com.yoong.sunnyside.domain.consumer.repository.ConsumerRepository
import com.yoong.sunnyside.domain.reply.dto.ReplyRequest
import com.yoong.sunnyside.domain.reply.service.ReplyService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CommunityReplyService(
    private val communityReplyRepository: CommunityReplyJpaRepository,
    private val communityCommentRepository: CommunityCommentJpaRepository,
    private val consumerRepository: ConsumerRepository,
): ReplyService {

    @Transactional
    override fun createReply(replyRequest: ReplyRequest, id: Long, authorId: Long): DefaultResponse {

        val communityComment = communityCommentRepository.findByIdAndConsumerId(id, authorId) ?: throw ModelNotFoundException("community is not found")
        val consumer = consumerRepository.findByIdOrNull(authorId) ?: throw ModelNotFoundException("consumer is not found")

        communityReplyRepository.save(CommunityReply(replyRequest, communityComment, consumer))

        return DefaultResponse("CommunityReply created successfully")
    }

    @Transactional
    override fun updateReply(replyRequest: ReplyRequest, id: Long, authorId: Long): DefaultResponse {

        val communityReply = communityReplyRepository.findByIdAndConsumerId(id, authorId) ?: throw ModelNotFoundException("CommunityReply not found")

        communityReply.update(replyRequest)

        return DefaultResponse("CommunityReply updated successfully")
    }

    @Transactional
    override fun deleteReply(id: Long, authorId: Long): DefaultResponse {

        val communityReply = communityReplyRepository.findByIdAndConsumerId(id, authorId) ?: throw ModelNotFoundException("comment not found")

        communityReply.delete()

        return DefaultResponse("CommunityReply deleted successfully")
    }

    override fun reportReply(id: Long, authorId: Long): DefaultResponse {
        TODO("Not yet implemented")
    }
}