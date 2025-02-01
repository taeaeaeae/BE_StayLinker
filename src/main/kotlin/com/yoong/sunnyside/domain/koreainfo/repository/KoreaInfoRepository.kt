package com.yoong.sunnyside.domain.koreainfo.repository

import com.yoong.sunnyside.domain.koreainfo.entity.KoreaInfo
import org.springframework.data.jpa.repository.JpaRepository

interface KoreaInfoRepository : JpaRepository<KoreaInfo, Long>, KoreaInfoQuerydslRepository {
}