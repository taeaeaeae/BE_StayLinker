package com.yoong.sunnyside.domain.community.dto

import com.yoong.sunnyside.common.type_class.Cursor

data class AllCommunityResponse(
    val posts: List<CommunityListResponse>,
    val nextCursor: Cursor,
){
    companion object {
        fun from(communityList: List<CommunityProjectionDto>, lastCursor: Cursor): AllCommunityResponse {
          return AllCommunityResponse(
              posts = communityList.map { CommunityListResponse.from(it) },
              nextCursor = lastCursor,
          )
        }
    }
}