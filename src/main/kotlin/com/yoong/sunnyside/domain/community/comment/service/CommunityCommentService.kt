package com.yoong.sunnyside.domain.community.comment.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.ModelNotFoundException
import com.yoong.sunnyside.domain.community.comment.entity.CommunityComment
import com.yoong.sunnyside.domain.community.comment.repository.CommunityCommentJpaRepository
import com.yoong.sunnyside.domain.community.comment.repository.CommunityReplyJpaRepository
import com.yoong.sunnyside.domain.community.repository.CommunityRepository
import com.yoong.sunnyside.domain.consumer.repository.ConsumerRepository
import com.yoong.sunnyside.domain.reply.dto.ReplyRequest
import com.yoong.sunnyside.domain.reply.service.ReplyService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CommunityCommentService(
    private val communityCommentRepository: CommunityCommentJpaRepository,
    private val communityRepository: CommunityRepository,
    private val consumerRepository: ConsumerRepository,
    private val communityReplyRepository: CommunityReplyJpaRepository,
): ReplyService {

    @Transactional
    override fun createReply(replyRequest: ReplyRequest, id: Long, authorId: Long): DefaultResponse {

        val community = communityRepository.findByIdOrNull(id) ?: throw ModelNotFoundException("community is not found")
        val consumer = consumerRepository.findByIdOrNull(authorId) ?: throw ModelNotFoundException("consumer is not found")

        communityCommentRepository.save(CommunityComment(replyRequest, community, consumer))

        return DefaultResponse("CommunityComment created successfully")
    }

    @Transactional
    override fun updateReply(replyRequest: ReplyRequest, id: Long, authorId: Long): DefaultResponse {

        val comment = communityCommentRepository.findByIdAndConsumerId(id, authorId) ?: throw ModelNotFoundException("comment not found")

        comment.update(replyRequest)

        return DefaultResponse("CommunityComment updated successfully")
    }

    @Transactional
    override fun deleteReply(id: Long, authorId: Long): DefaultResponse {

        val comment = communityCommentRepository.findByIdAndConsumerId(id, authorId) ?: throw ModelNotFoundException("comment not found")

        val commentReplies = communityReplyRepository.findAllByCommentId(comment.id!!)

        if(commentReplies.isNotEmpty()) {
            commentReplies.forEach{
                it.delete()
            }
        }

        comment.delete()

        return DefaultResponse("CommunityComment deleted successfully")
    }

    override fun reportReply(id: Long, authorId: Long): DefaultResponse {
        TODO("Not yet implemented")
    }
}