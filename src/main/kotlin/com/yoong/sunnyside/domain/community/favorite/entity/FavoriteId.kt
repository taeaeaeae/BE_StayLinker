package com.yoong.sunnyside.domain.community.favorite.entity

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class FavoriteId (
    val consumerId: Long? = null,
    val communityId: Long? = null
): Serializable