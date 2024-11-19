package com.yoong.sunnyside.domain.community.repository

import com.yoong.sunnyside.domain.community.entity.Community
import org.springframework.data.jpa.repository.JpaRepository

interface CommunityJpaRepository: JpaRepository<Community, Long> {

    fun findByIdAndConsumerId(id: Long, consumerId: Long): Community?
    fun findAllByIdIn(communityId: List<Long>): List<Community>
}