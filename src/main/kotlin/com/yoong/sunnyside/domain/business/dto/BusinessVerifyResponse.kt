package com.yoong.sunnyside.domain.business.dto

import java.time.LocalDateTime

data class BusinessVerifyResponse(
    val businessCode: String,           //사업자등록번호
    val registrationCode: String,       //부동산등록번호
    val agentName: String,              //대표자
    val businessName: String,           //상호명
    val registDate: String,             //창립일자
    val mnnmAddress: String,            //지번주소
    val rdnmAddress: String,            //도로명주소
    val estbsBeginDe: LocalDateTime,    //보증설정시작일
    val estbsEndDe: LocalDateTime       //보증설정만료일
)
