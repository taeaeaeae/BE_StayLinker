package com.yoong.sunnyside.domain.community.dto

import com.yoong.sunnyside.domain.community.enum_class.CommunityType

data class CommunityRequest(
    val title: String,
    val description: String,
    val communityType: CommunityType,
)