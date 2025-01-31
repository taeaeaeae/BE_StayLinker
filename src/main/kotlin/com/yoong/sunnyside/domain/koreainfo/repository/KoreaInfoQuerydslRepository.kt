package com.yoong.sunnyside.domain.koreainfo.repository

import com.yoong.sunnyside.common.enum_class.SearchType
import com.yoong.sunnyside.domain.koreainfo.entity.KoreaInfo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface KoreaInfoQuerydslRepository {
    fun findPage(pageable: Pageable, division: String?, searchType: SearchType?, keyword: String?): Page<KoreaInfo>
    // division 종류가 정해지지 않아서 String으로 해둠
}