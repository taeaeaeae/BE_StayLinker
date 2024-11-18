package com.yoong.sunnyside.domain.community.service

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.common.exception.ModelNotFoundException
import com.yoong.sunnyside.domain.community.dto.AllCommunityResponse
import com.yoong.sunnyside.domain.community.dto.CommunityRequest
import com.yoong.sunnyside.domain.community.dto.CommunityResponse
import com.yoong.sunnyside.domain.community.entity.Community
import com.yoong.sunnyside.domain.community.repository.CommunityRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CommunityService(
    private val communityRepository: CommunityRepository,
){

    @Transactional
    fun createCommunity(communityRequest: CommunityRequest, id: Long): DefaultResponse {

        communityRepository.save(Community(communityRequest, id))

        return DefaultResponse("Community created")
    }

    fun getAllCommunity(cursor: Long?, limit: Int, search: String?): List<AllCommunityResponse> {

        val communities = communityRepository.findAll(cursor, limit, search)

        return communities.map { AllCommunityResponse.from(it) }
    }

    fun getCommunity(communityId: Long): CommunityResponse {

        val communityTuple =  communityRepository.findById(communityId)

        return communityTuple
    }

    @Transactional
    fun updateCommunity(communityRequest: CommunityRequest, communityId: Long, consumerId: Long): DefaultResponse {

        val community = communityRepository.findByIdAndConsumerId(communityId, consumerId) ?: throw ModelNotFoundException("Community is not found")

        community.update(communityRequest)

        return DefaultResponse("Community updated successfully")
    }

    @Transactional
    fun deleteCommunity(communityId: Long, consumerId: Long): DefaultResponse {

        val community = communityRepository.findByIdAndConsumerId(communityId, consumerId) ?: throw ModelNotFoundException("Community is not found")

        community.delete()

        return DefaultResponse("Community deleted successfully")
    }

    fun reportCommunity(communityId: Long): DefaultResponse {
        TODO()
    }
}