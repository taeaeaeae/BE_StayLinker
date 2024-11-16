package com.yoong.sunnyside.domain.auth.dto

import com.yoong.sunnyside.infra.security.MemberRole

data class MemberRoleResponse (

    val id: Long,
    val role: MemberRole
)