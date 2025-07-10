package com.yoong.sunnyside.domain.business.dto

data class BusinessSearchListResponse(
    val totalPage: Int,
    val result: List<BusinessSearchResultResponse>
)