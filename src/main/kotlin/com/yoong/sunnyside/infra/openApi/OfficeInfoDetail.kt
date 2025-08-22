package com.yoong.sunnyside.infra.openApi

import com.fasterxml.jackson.annotation.JsonProperty

data class OfficeInfoDetail(
    @JsonProperty("field")
    val field: List<BusinessResultResponse>,// = emptyList()field
    val pageNo: Int,
    val totalCount: Int,
) {
//    val field: List<BusinessResultResponse> = emptyList()
}