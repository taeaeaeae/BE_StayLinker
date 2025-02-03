package com.yoong.sunnyside.domain.community.repository

import com.yoong.sunnyside.domain.community.dto.CommunityProjectionDto
import com.yoong.sunnyside.domain.community.dto.CommunityResponse
import com.yoong.sunnyside.domain.community.entity.Community
import com.yoong.sunnyside.domain.community.enum_class.CommunityType
import com.yoong.sunnyside.domain.community.enum_class.SetOrder

interface CommunityRepository {
    fun save(community: Community): Community
    fun findByIdOrNull(communityId: Long): Community?
    fun findAll(cursor: String?, limit: Int, search: String?, communityType: CommunityType, setOrder: SetOrder): List<CommunityProjectionDto>
    fun findByIdAndConsumerId(id: Long, consumerId: Long): Community?
    fun findById(communityId: Long): CommunityResponse
    fun findAllByIdIn(ids: List<Long>): List<Community>
}