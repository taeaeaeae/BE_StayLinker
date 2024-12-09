package com.yoong.sunnyside.domain.community.comment.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.ModelNotFoundException
import com.yoong.sunnyside.domain.community.comment.entity.CommunityComment
import com.yoong.sunnyside.domain.community.comment.entity.CommunityReply
import com.yoong.sunnyside.domain.community.comment.repository.CommunityCommentJpaRepository
import com.yoong.sunnyside.domain.community.comment.repository.CommunityReplyJpaRepository
import com.yoong.sunnyside.domain.community.entity.Community
import com.yoong.sunnyside.domain.community.enum_class.CommunityType
import com.yoong.sunnyside.domain.consumer.entity.Consumer
import com.yoong.sunnyside.domain.consumer.repository.ConsumerJpaRepository
import com.yoong.sunnyside.domain.consumer.repository.ConsumerRepository
import com.yoong.sunnyside.domain.reply.dto.ReplyRequest
import com.yoong.sunnyside.infra.security.MemberRole
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import org.springframework.data.repository.findByIdOrNull

class CommunityReplyServiceTest : StringSpec({


    val communityReplyRepository = mockk<CommunityReplyJpaRepository>()
    val communityCommentRepository = mockk<CommunityCommentJpaRepository>()
    val consumerRepository = mockk<ConsumerRepository>()
    val communityReplyService = CommunityReplyService(communityReplyRepository, communityCommentRepository, consumerRepository)



    "커뮤니티 댓글이 없을 경우 ModelNotFoundException"{

        //Given
        val replyRequest = ReplyRequest(
            "description",
        )

         every { communityCommentRepository.findByIdAndConsumerId(any(), any()) } returns null

        //When & Then

        shouldThrow<ModelNotFoundException> {
            communityReplyService.createReply(replyRequest, 1L, 1L)
        }.let {
            it.message shouldBe "community is not found"
        }
    }

    "소비자가 없을 경우 ModelNotFoundException"{

        //Given
        val replyRequest = ReplyRequest(
            "description",
        )

        every { communityCommentRepository.findByIdAndConsumerId(any(), any()) } returns COMMUNITY_COMMENT
        every { consumerRepository.findByIdOrNull(any()) } returns null

        //When & Then

        shouldThrow<ModelNotFoundException> {
            communityReplyService.createReply(replyRequest, 1L, 1L)
        }.let {
            it.message shouldBe "consumer is not found"
        }
    }

    "답글 정상 작성"{

        //Given
        val replyRequest = ReplyRequest(
            "description",
        )

        val communityReply = CommunityReply(
            replyRequest = replyRequest,
            communityComment = COMMUNITY_COMMENT,
            consumer = CONSUMER
        )

        every { communityCommentRepository.findByIdAndConsumerId(any(), any()) } returns COMMUNITY_COMMENT
        every { consumerRepository.findByIdOrNull(any()) } returns CONSUMER
        every { communityReplyRepository.save(any()) } answers {
            communityReply.id = 1L
            communityReply
        }
        every { communityReplyRepository.findByIdOrNull(any()) } returns communityReply

        //When & Then

        val result = communityReplyService.createReply(replyRequest, 1L, 1L)
        val communityReplyData = communityReplyRepository.findByIdOrNull(communityReply.id)
        result shouldBe DefaultResponse("CommunityReply created successfully")
        communityReplyData!!.description shouldBe "description"
    }

    "답글 정상 수정"{

        //Given
        val replyRequest = ReplyRequest(
            "description2",
        )

        val communityReply = CommunityReply(
            replyRequest = ReplyRequest("description"),
            communityComment = COMMUNITY_COMMENT,
            consumer = CONSUMER
        )

        every { communityReplyRepository.findByIdAndConsumerId(any(), any()) } answers  {
            communityReply.id = 1L
            communityReply
        }

        //When & Then

        val result = communityReplyService.updateReply(replyRequest, 1L, 1L)
        result shouldBe DefaultResponse("CommunityReply updated successfully")
        communityReply.description shouldBe "description2"
    }

    "답글 정상 삭제"{

        //Given
        val replyRequest = ReplyRequest(
            "description2",
        )

        val communityReply = CommunityReply(
            replyRequest = ReplyRequest("description"),
            communityComment = COMMUNITY_COMMENT,
            consumer = CONSUMER
        )

        every { communityReplyRepository.findByIdAndConsumerId(any(), any()) } answers  {
            communityReply.id = 1L
            communityReply
        }

        //When & Then

        val result = communityReplyService.deleteReply(1L, 1L)
        result shouldBe DefaultResponse("CommunityReply deleted successfully")
        communityReply.deletedAt shouldNotBe null
    }
}){

    companion object{

        private val COMMUNITY = Community(
            consumerId = 1L,
            title = "test",
            description = "test2",
            communityType = CommunityType.SMALL_TALKING
        )

        val CONSUMER = Consumer(
            email = "test@test",
            password = "test",
            address = "test@test",
            nickname = "testtest",
            country = "Republic Of Korea",
            phoneNumber = "1234567890",
            foreignNumber = "1234567890",
            foreignCreateAt = "2020-01-01T00:00:00.000Z",
            role = MemberRole.CONSUMER
        )

        val COMMUNITY_COMMENT = CommunityComment(
            community = COMMUNITY,
            consumer = CONSUMER,
            description = "test"
        )
    }
}
