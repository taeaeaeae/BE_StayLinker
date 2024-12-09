package com.yoong.sunnyside.domain.community.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.community.comment.entity.CommunityComment
import com.yoong.sunnyside.domain.community.comment.entity.CommunityReply
import com.yoong.sunnyside.domain.community.dto.CommunityRequest
import com.yoong.sunnyside.domain.community.dto.CommunityResponse
import com.yoong.sunnyside.domain.community.entity.Community
import com.yoong.sunnyside.domain.community.enum_class.CommunityType
import com.yoong.sunnyside.domain.community.repository.CommunityRepository
import com.yoong.sunnyside.domain.consumer.entity.Consumer
import com.yoong.sunnyside.domain.reply.dto.ReplyRequest
import com.yoong.sunnyside.infra.security.MemberRole
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk

class CommunityServiceTest : StringSpec({

    val communityRepository = mockk<CommunityRepository>()
    val communityService = CommunityService(communityRepository)

    "커뮤니티 글 생성 정상 동작"{
        val communityRequest = CommunityRequest(
            title = "title",
            description = "description",
            communityType = CommunityType.SMALL_TALKING,
        )

        val community = Community(
            communityRequest = communityRequest,
            consumerId = 1L
        )

        every { communityRepository.save(any()) } answers {
            community.id = 1L
            community
        }

        val result = communityService.createCommunity(communityRequest, 1L)
        result shouldBe DefaultResponse("Community created")
        community.title shouldBe "title"
        community.description shouldBe "description"
        community.communityType shouldBe CommunityType.SMALL_TALKING
    }

    "커뮤니티 글 전체 조회 정상 동작"{
        val communityRequest = CommunityRequest(
            title = "title",
            description = "description",
            communityType = CommunityType.SMALL_TALKING,
        )

        val community3 = Community(
            communityRequest = communityRequest,
            consumerId = 1L
        )

        val community4 = Community(
            communityRequest = communityRequest,
            consumerId = 1L
        )

        val community5 = Community(
            communityRequest = communityRequest,
            consumerId = 1L
        )

        community3.apply { id = 3 }
        community4.apply { id = 4 }
        community5.apply { id = 5 }

        every { communityRepository.findAll(any(), any(), any()) } returns listOf(community3, community4, community5)

        val result = communityService.getAllCommunity(2, 1, null)

        result.size shouldBe 3

    }

    "커뮤니티 글 단건 조회 정상 동작 (댓글, 답글 포함)"{
        val communityRequest = CommunityRequest(
            title = "title",
            description = "description",
            communityType = CommunityType.SMALL_TALKING,
        )

        val community1 = Community(
            communityRequest = communityRequest,
            consumerId = 1L
        )

        community1.apply { id = 1 }
        COMMUNITY_COMMENT1.apply{ id = 1 }
        COMMUNITY_COMMENT1.apply { consumer.id = 1 }
        COMMUNITY_COMMENT2.apply{ id = 2 }
        COMMUNITY_COMMENT2.apply { consumer.id = 1 }
        COMMENT_REPLY.apply{ id = 1 }

        every { communityRepository.findById(any()) } returns CommunityResponse.from(
            community1,
            listOf(COMMUNITY_COMMENT1, COMMUNITY_COMMENT2),
            listOf(COMMENT_REPLY))

        val result = communityService.getCommunity(1)

        result.title shouldBe "title"
        result.description shouldBe "description"
        result.replies.size shouldBe 2
        result.replies[0].replies.size shouldBe 1

    }

    "커뮤니티 글 수정 정상 동작"{
        val communityRequest = CommunityRequest(
            title = "title3",
            description = "description3",
            communityType = CommunityType.USED_TRADE,
        )

        val community = Community(
            communityRequest = communityRequest,
            consumerId = 1L
        )

        every { communityRepository.findByIdAndConsumerId(any(), any()) } answers {
            community.id = 1
            community
        }

        val result = communityService.updateCommunity(communityRequest, 1L, 1L)
        result shouldBe DefaultResponse("Community updated successfully")
        community.title shouldBe "title3"
        community.description shouldBe "description3"
        community.communityType shouldBe CommunityType.USED_TRADE
    }

    "커뮤니티 글 삭제 정상 동작"{
        val communityRequest = CommunityRequest(
            title = "title3",
            description = "description3",
            communityType = CommunityType.USED_TRADE,
        )

        val community = Community(
            communityRequest = communityRequest,
            consumerId = 1L
        )

        every { communityRepository.findByIdAndConsumerId(any(), any()) } answers {
            community.id = 1
            community
        }

        val result = communityService.deleteCommunity(1L, 1L)
        result shouldBe DefaultResponse("Community deleted successfully")
        community.deletedAt shouldNotBe null
    }
}){
    companion object {
        private val COMMUNITY = Community(
            consumerId = 1L,
            title = "test",
            description = "test2",
            communityType = CommunityType.SMALL_TALKING
        )

        private val CONSUMER = Consumer(
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

        val COMMUNITY_COMMENT1 = CommunityComment(
            community = COMMUNITY,
            consumer = CONSUMER,
            description = "test1"
        )

        val COMMUNITY_COMMENT2 = CommunityComment(
            community = COMMUNITY,
            consumer = CONSUMER,
            description = "test2"
        )

        val COMMENT_REPLY = CommunityReply(
            communityComment = COMMUNITY_COMMENT1,
            consumer = CONSUMER,
            replyRequest = ReplyRequest("test3")
        )
    }
}
