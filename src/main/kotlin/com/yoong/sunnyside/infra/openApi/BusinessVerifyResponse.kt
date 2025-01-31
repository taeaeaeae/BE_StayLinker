package com.yoong.sunnyside.infra.openApi

data class BusinessVerifyResponse(
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
)
