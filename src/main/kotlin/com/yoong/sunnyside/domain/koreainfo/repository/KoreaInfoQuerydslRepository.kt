package com.yoong.sunnyside.domain.koreainfo.repository

import com.yoong.sunnyside.domain.koreainfo.entity.KoreaInfo

interface KoreaInfoQuerydslRepository {
    fun findPage(
        pageSize: Long, cursor: Any?, division: String?, keyword: String?
    ): List<KoreaInfo>
}