package com.yoong.sunnyside.domain.community.favorite.repository

import com.yoong.sunnyside.domain.community.favorite.entity.CommunityFavorite
import org.springframework.data.jpa.repository.JpaRepository

interface CommunityFavoriteJpaRepository: JpaRepository<CommunityFavorite, Long> {

    fun findByCommunityIdAndConsumerId(communityId: Long, consumerId: Long): CommunityFavorite?
    fun findByConsumerId(consumerId: Long): List<CommunityFavorite>
}