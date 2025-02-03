package com.yoong.sunnyside.domain.community.dto

import com.querydsl.core.annotations.QueryProjection
import com.yoong.sunnyside.domain.community.entity.Community

data class CommunityProjectionDto(
    val community: Community,
    val favoriteCount : Long
)
