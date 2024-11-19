package com.yoong.sunnyside.domain.community.favorite.entity

import com.yoong.sunnyside.domain.community.entity.Community
import com.yoong.sunnyside.domain.consumer.entity.Consumer
import jakarta.persistence.*

@IdClass(FavoriteId::class)
@Entity
@Table(name = "community_favorite")
class CommunityFavorite(

    @Id
    @Column(name = "consumer_id", nullable = false)
    val consumerId: Long,

    @Id
    @Column(name = "community_id", nullable = false)
    val communityId: Long
){
}