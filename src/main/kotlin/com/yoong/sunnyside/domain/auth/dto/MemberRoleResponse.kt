package com.yoong.sunnyside.domain.auth.dto

import com.yoong.sunnyside.infra.security.MemberPrincipal

data class MemberRoleResponse (

    val id: Long,
    val role: String,
) {

    companion object {
        fun from(principal: MemberPrincipal): MemberRoleResponse {

            return MemberRoleResponse(
                id = principal.id,
                role = principal.authorities.toString().substring(6, principal.authorities.toString().length - 1)
            )
        }
    }
}