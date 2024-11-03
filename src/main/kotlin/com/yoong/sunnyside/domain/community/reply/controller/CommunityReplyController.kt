package com.yoong.sunnyside.domain.community.reply.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.community.reply.dto.CommunityReplyRequestDto
import com.yoong.sunnyside.domain.community.reply.service.CommunityReplyService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@Tag(name = "커뮤니티 댓글 관련 API")
@RestController
@RequestMapping("/community/reply")
class CommunityReplyController(
    private val communityReplyService: CommunityReplyService
){

    @Operation(summary = "커뮤니티 댓글 생성 API", description = "커뮤니티 id 값을 넣어 주시면 됩니다")
    @PostMapping("/{communityId}")
    fun createReply(
        @PathVariable communityId: Long,
        @RequestBody communityReplyRequestDto: CommunityReplyRequestDto
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(communityReplyService.createReply(communityReplyRequestDto, communityId))

    @Operation(summary = "커뮤니티 댓글 수정 API", description = "커뮤니티 댓글 id 값을 넣어 주시면 됩니다")
    @PutMapping("/{replyId}")
    fun updateReply(
        @PathVariable("replyId") replyId: Long,
        @RequestBody communityReplyRequestDto: CommunityReplyRequestDto
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(communityReplyService.updateReply(communityReplyRequestDto, replyId))

    @Operation(summary = "커뮤니티 글 삭제 API", description = "커뮤니티 댓글 id 값을 넣어 주시면 됩니다")
    @DeleteMapping("/{replyId}")
    fun deleteCommunity(
        @PathVariable("replyId") replyId: Long,
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(communityReplyService.deleteReply(replyId))
}