package com.yoong.sunnyside.domain.community.dto

data class AllCommunityResponse(
    val posts: List<CommunityListResponse>,
    val nextCursor: Any,
){
    companion object {
        fun from(communityList: List<CommunityProjectionDto>, lastCursor: Any): AllCommunityResponse {
          return AllCommunityResponse(
              posts = communityList.map { CommunityListResponse.from(it) },
              nextCursor = lastCursor,
          )
        }
    }
}