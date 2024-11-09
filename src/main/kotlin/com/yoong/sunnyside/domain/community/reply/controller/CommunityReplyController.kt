package com.yoong.sunnyside.domain.community.reply.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.community.reply.service.CommunityReplyService
import com.yoong.sunnyside.domain.reply.controller.ReplyController
import com.yoong.sunnyside.domain.reply.dto.ReplyRequestDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@Tag(name = "커뮤니티 댓글 관련 API")
@RestController
@RequestMapping("/community/reply")
class CommunityReplyController(
    private val communityReplyService: CommunityReplyService,
):ReplyController {

    @Operation(summary = "커뮤니티 댓글 생성 API", description = "커뮤니티 id 값을 넣어 주시면 됩니다")
    @PostMapping("/{id}")
    override fun createReply(
        @PathVariable id: Long,
        @RequestBody replyRequestDto: ReplyRequestDto
    ): ResponseEntity<DefaultResponseDto> =
        ResponseEntity.status(HttpStatus.OK).body(communityReplyService.createReply(replyRequestDto, id))

    @Operation(summary = "커뮤니티 댓글 수정 API", description = "커뮤니티 댓글 id 값을 넣어 주시면 됩니다")
    @PutMapping("/{id}")
    override fun updateReply(
        @PathVariable id: Long,
        @RequestBody replyRequestDto: ReplyRequestDto
    ): ResponseEntity<DefaultResponseDto> =
        ResponseEntity.status(HttpStatus.OK).body(communityReplyService.updateReply(replyRequestDto, id))

    @Operation(summary = "커뮤니티 댓글 삭제 API", description = "커뮤니티 댓글 id 값을 넣어 주시면 됩니다")
    @DeleteMapping("/{id}")
    override fun deleteReply(
        @PathVariable id: Long,
    ): ResponseEntity<DefaultResponseDto> = ResponseEntity.status(HttpStatus.OK).body(communityReplyService.deleteReply(id))

    @Operation(summary = "커뮤니티 댓글 신고 API", description = "커뮤니티 댓글 id 값을 넣어 주시면 됩니다")
    @PatchMapping("/report/{id}")
    override fun reportReply(
        @PathVariable id: Long
    ): ResponseEntity<DefaultResponseDto> = ResponseEntity.status(HttpStatus.OK).body(communityReplyService.reportReply(id))
}