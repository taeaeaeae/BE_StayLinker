package com.yoong.sunnyside.domain.business.dto

data class BusinessVerifyRequest(
    val businessCode: String,     //사업자등록번호
    val registrationCode: String,   //부동산등록번호
    val name: String,               //상호명
    val agentName: String,          //대표자
    val registDate: String          //창립일자
)
