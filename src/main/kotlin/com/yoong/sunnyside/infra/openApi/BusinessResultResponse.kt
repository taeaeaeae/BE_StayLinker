package com.yoong.sunnyside.infra.openApi

import com.yoong.sunnyside.domain.business.dto.BusinessSearchResultResponse
import java.time.LocalDateTime

data class BusinessResultResponse(
    val jurirno: String,
    val brkrNm: String,
    val ldCode: String,
    val ldCodeNm: String,
    val registDe: String,
    val sttusSeCodeNm: String,
    val rdnmadrcode: String,
    val mnnmadr: String,
    val rdnmadr: String,
    val estbsBeginDe: String,
    val lastUpdtDt: String,
    val estbsEndDe: String,
    val sttusSeCode: String,
    val bsnmCmpnm: String
) {
    fun toResponse(): BusinessSearchResultResponse {
        return BusinessSearchResultResponse(
            businessName = bsnmCmpnm,
            address = "${rdnmadr}(${mnnmadr})",
            businessCertificate = jurirno,
            agentName = brkrNm
        )
    }
}