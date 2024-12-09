package com.yoong.sunnyside.domain.community.favorite.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.ModelNotFoundException
import com.yoong.sunnyside.domain.community.dto.AllCommunityResponse
import com.yoong.sunnyside.domain.community.favorite.entity.CommunityFavorite
import com.yoong.sunnyside.domain.community.favorite.repository.CommunityFavoriteJpaRepository
import com.yoong.sunnyside.domain.community.repository.CommunityRepository
import com.yoong.sunnyside.domain.favorite.service.FavoriteService
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class CommunityFavoriteService(
    private val communityFavoriteRepository: CommunityFavoriteJpaRepository,
    private val communityRepository: CommunityRepository
): FavoriteService {

    @Transactional
    override fun createFavorite(id: Long, memberId: Long): DefaultResponse {

        communityFavoriteRepository.save(CommunityFavorite(id, memberId))

        return DefaultResponse("favorite saved successfully")
    }


    @Transactional
    override fun deleteFavorite(id: Long, memberId: Long): DefaultResponse {

        val communityFavorite = communityFavoriteRepository.findByCommunityIdAndConsumerId(id, memberId) ?: throw ModelNotFoundException("favorite not found")

        communityFavoriteRepository.delete(communityFavorite)

        return DefaultResponse("favorite deleted successfully")
    }

    fun getFavorite(memberId: Long): List<AllCommunityResponse> {

        val communityFavorites = communityFavoriteRepository.findByConsumerId(memberId)

        val communityIds = communityFavorites.map{ it.communityId }

        val communities = communityRepository.findAllByIdIn(communityIds)

        return communities.map { AllCommunityResponse.from(it) }
    }
}