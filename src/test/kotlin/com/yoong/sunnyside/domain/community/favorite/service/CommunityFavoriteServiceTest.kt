package com.yoong.sunnyside.domain.community.favorite.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.ModelNotFoundException
import com.yoong.sunnyside.domain.community.dto.AllCommunityResponse
import com.yoong.sunnyside.domain.community.entity.Community
import com.yoong.sunnyside.domain.community.enum_class.CommunityType
import com.yoong.sunnyside.domain.community.favorite.entity.CommunityFavorite
import com.yoong.sunnyside.domain.community.favorite.repository.CommunityFavoriteJpaRepository
import com.yoong.sunnyside.domain.community.repository.CommunityRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk

class CommunityFavoriteServiceTest : StringSpec({

    val communityFavoriteRepository =  mockk<CommunityFavoriteJpaRepository>()
    val communityRepository = mockk<CommunityRepository>()
    val communityFavoriteService = CommunityFavoriteService(communityFavoriteRepository, communityRepository)

    "찜하기 정상 동작"{

        val communityFavorite = CommunityFavorite(
            1L, 1L
        )

        every { communityFavoriteRepository.save(any()) } returns communityFavorite
        every { communityFavoriteRepository.findByCommunityIdAndConsumerId(any(), any()) } returns communityFavorite

        val result = communityFavoriteService.createFavorite(1L, 1L)
        val communityFavoriteData = communityFavoriteRepository.findByCommunityIdAndConsumerId(1L, 1L)

        result shouldBe DefaultResponse("favorite saved successfully")
        communityFavoriteData!!.communityId shouldBe 1L
        communityFavoriteData.consumerId shouldBe 1L

    }

    "찜하기 삭제 정상 동작"{

        val communityFavorite = CommunityFavorite(
            1L, 1L
        )

        every { communityFavoriteRepository.findByCommunityIdAndConsumerId(any(), any()) } returns communityFavorite
        every { communityFavoriteRepository.delete(any()) } returns Unit

        val result = communityFavoriteService.deleteFavorite(1L, 1L)
        result shouldBe DefaultResponse("favorite deleted successfully")
    }

    "나의 찜 목록 불러오기"{

        val communityFavorite1 = CommunityFavorite(
            1L, 1L
        )

        val communityFavorite2 = CommunityFavorite(
            1L, 1L
        )

        COMMUNITY1.apply { id = 1L }
        COMMUNITY2.apply { id = 2L }

        every { communityFavoriteRepository.findByConsumerId(any()) } returns listOf(communityFavorite1, communityFavorite2)
        every { communityRepository.findAllByIdIn(any()) } returns listOf(COMMUNITY1, COMMUNITY2)

        val result = communityFavoriteService.getFavorite(1L)
        result.size shouldBe 2
        result[0] shouldBe AllCommunityResponse.from(COMMUNITY1)
        result[1] shouldBe AllCommunityResponse.from(COMMUNITY2)
    }

}){

    companion object{
        private val COMMUNITY1 = Community(
            consumerId = 1L,
            title = "test",
            description = "test3",
            communityType = CommunityType.SMALL_TALKING
        )

        private val COMMUNITY2 = Community(
            consumerId = 1L,
            title = "test2",
            description = "test2",
            communityType = CommunityType.USED_TRADE
        )
    }
}
