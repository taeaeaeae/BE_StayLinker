package com.yoong.sunnyside.domain.community.repository

import com.yoong.sunnyside.domain.community.dto.CommunityResponse
import com.yoong.sunnyside.domain.community.entity.Community
import com.yoong.sunnyside.domain.community.enum_class.CommunityType

interface CommunityRepository {
    fun save(community: Community): Community
    fun findByIdOrNull(communityId: Long): Community?
    fun findAll(cursor: Long?, limit: Int, search: String?, communityType: CommunityType, orderBy: Boolean): List<Community>
    fun findByIdAndConsumerId(id: Long, consumerId: Long): Community?
    fun findById(communityId: Long): CommunityResponse
    fun findAllByIdIn(ids: List<Long>): List<Community>
}