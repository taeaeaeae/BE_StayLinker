package com.yoong.sunnyside.domain.community.comment.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.ModelNotFoundException
import com.yoong.sunnyside.domain.community.comment.entity.CommunityComment
import com.yoong.sunnyside.domain.community.comment.entity.CommunityReply
import com.yoong.sunnyside.domain.community.comment.repository.CommunityCommentJpaRepository
import com.yoong.sunnyside.domain.community.comment.repository.CommunityReplyJpaRepository
import com.yoong.sunnyside.domain.community.comment.service.CommunityReplyServiceTest.Companion
import com.yoong.sunnyside.domain.community.comment.service.CommunityReplyServiceTest.Companion.COMMUNITY_COMMENT
import com.yoong.sunnyside.domain.community.entity.Community
import com.yoong.sunnyside.domain.community.enum_class.CommunityType
import com.yoong.sunnyside.domain.community.repository.CommunityRepository
import com.yoong.sunnyside.domain.consumer.entity.Consumer
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

class CommunityCommentServiceTest : StringSpec({

    val communityCommentRepository = mockk<CommunityCommentJpaRepository>()
    val communityRepository = mockk<CommunityRepository>()
    val consumerRepository = mockk<ConsumerRepository>()
    val communityReplyRepository = mockk<CommunityReplyJpaRepository>()
    val communityCommentService = CommunityCommentService(communityCommentRepository, communityRepository, consumerRepository, communityReplyRepository)

    "커뮤니티 댓글이 없을 경우 ModelNotFoundException"{

        //Given
        val replyRequest = ReplyRequest(
            "description",
        )

        every { communityRepository.findByIdOrNull(any()) } returns null

        //When & Then

        shouldThrow<ModelNotFoundException> {
            communityCommentService.createReply(replyRequest, 1L, 1L)
        }.let {
            it.message shouldBe "community is not found"
        }
    }

    "소비자가 없을 경우 ModelNotFoundException"{

        //Given
        val replyRequest = ReplyRequest(
            "description",
        )

        every { communityRepository.findByIdOrNull(any()) } returns COMMUNITY
        every { consumerRepository.findByIdOrNull(any()) } returns null

        //When & Then

        shouldThrow<ModelNotFoundException> {
            communityCommentService.createReply(replyRequest, 1L, 1L)
        }.let {
            it.message shouldBe "consumer is not found"
        }
    }

    "답글 정상 작성"{

        //Given
        val replyRequest = ReplyRequest(
            "description",
        )

        val communityComment = CommunityComment(
            replyRequest = replyRequest,
            community = COMMUNITY,
            consumer = CONSUMER
        )

        every { communityRepository.findByIdOrNull(any()) } returns COMMUNITY
        every { consumerRepository.findByIdOrNull(any()) } returns CONSUMER
        every { communityCommentRepository.save(any()) } answers {
            communityComment.id = 1L
            communityComment
        }
        every { communityCommentRepository.findByIdOrNull(any()) } returns communityComment

        //When & Then

        val result = communityCommentService.createReply(replyRequest, 1L, 1L)
        val communityReplyData = communityCommentRepository.findByIdOrNull(communityComment.id)
        result shouldBe DefaultResponse("CommunityComment created successfully")
        communityReplyData!!.description shouldBe "description"
    }

    "답글 정상 수정"{

        //Given
        val replyRequest = ReplyRequest(
            "description2",
        )

        val communityComment = CommunityComment(
            replyRequest = replyRequest,
            community = COMMUNITY,
            consumer = CommunityReplyServiceTest.CONSUMER
        )

        every { communityCommentRepository.findByIdAndConsumerId(any(), any()) } answers  {
            communityComment.id = 1L
            communityComment
        }

        //When & Then

        val result = communityCommentService.updateReply(replyRequest, 1L, 1L)
        result shouldBe DefaultResponse("CommunityComment updated successfully")
        communityComment.description shouldBe "description2"
    }

    "답글 정상 삭제"{

        //Given
        val replyRequest = ReplyRequest(
            "description2",
        )

        val communityComment = CommunityComment(
            replyRequest = replyRequest,
            community = COMMUNITY,
            consumer = CommunityReplyServiceTest.CONSUMER
        )

        every { communityCommentRepository.findByIdAndConsumerId(any(), any()) } answers  {
            communityComment.id = 1L
            communityComment
        }
        every { communityReplyRepository.findAllByCommentId(any()) } returns emptyList()

        //When & Then

        val result = communityCommentService.deleteReply(1L, 1L)
        result shouldBe DefaultResponse("CommunityComment deleted successfully")
        communityComment.deletedAt shouldNotBe null
    }

}) {
    companion object {

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

    }
}
