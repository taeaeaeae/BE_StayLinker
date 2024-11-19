package com.yoong.sunnyside.domain.community.favorite.entity

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class FavoriteId (
    val consumer: Long = 0,
    val community: Long = 0
): Serializable