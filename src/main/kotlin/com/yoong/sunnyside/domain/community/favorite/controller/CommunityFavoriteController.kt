package com.yoong.sunnyside.domain.community.favorite.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.community.dto.AllCommunityResponseDto
import com.yoong.sunnyside.domain.community.favorite.service.CommunityFavoriteService
import com.yoong.sunnyside.domain.favorite.controller.FavoriteController
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@Tag(name = "커뮤니티 찜하기 관련 API")
@RestController
@RequestMapping("/community/favorite")
class CommunityFavoriteController(
    private val communityFavoriteService: CommunityFavoriteService
): FavoriteController{

    @Operation(summary = "커뮤니티 찜하기 API", description = "커뮤니티 id 값을 넣어 주시면 됩니다")
    @PostMapping("/{id}")
    override fun createFavorite(
        @PathVariable id: Long,
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(communityFavoriteService.createFavorite(id))

    @Operation(summary = "내 찜한 커뮤니티 게시글 조회 API")
    @GetMapping
    fun getFavorites(
    ): ResponseEntity<List<AllCommunityResponseDto>>
            = ResponseEntity.status(HttpStatus.OK).body(communityFavoriteService.getFavorite())

    @Operation(summary = "커뮤니티 찜하기 삭제 API", description = "커뮤니티 id 값을 넣어 주시면 됩니다")
    @DeleteMapping("/{id}")
    override fun deleteFavorite(
        @PathVariable id: Long,
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(communityFavoriteService.deleteFavorite(id))
}