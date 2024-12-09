package com.yoong.sunnyside.domain.community.comment.controller

import com.yoong.sunnyside.common.dto.DefaultResponse
import com.yoong.sunnyside.domain.community.comment.service.CommunityCommentService
import com.yoong.sunnyside.domain.reply.controller.ReplyController
import com.yoong.sunnyside.domain.reply.dto.ReplyRequest
import com.yoong.sunnyside.infra.security.MemberPrincipal
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import java.security.Principal


@Tag(name = "커뮤니티 댓글 관련 API")
@RestController
@RequestMapping("/community/comment")
class CommunityCommentController(
    private val communityCommentService: CommunityCommentService,
):ReplyController {

    @Operation(summary = "커뮤니티 댓글 생성 API", description = "커뮤니티 id 값을 넣어 주시면 됩니다")
    @PreAuthorize("hasRole('CONSUMER')")
    @PostMapping("/{id}")
    override fun createReply(
        @PathVariable id: Long,
        @RequestBody replyRequest: ReplyRequest,
        @AuthenticationPrincipal principal: MemberPrincipal
    ): ResponseEntity<DefaultResponse> =
        ResponseEntity.status(HttpStatus.OK).body(communityCommentService.createReply(replyRequest, id, principal.id))

    @Operation(summary = "커뮤니티 댓글 수정 API", description = "커뮤니티 댓글 id 값을 넣어 주시면 됩니다")
    @PreAuthorize("hasRole('CONSUMER')")
    @PutMapping("/{id}")
    override fun updateReply(
        @PathVariable id: Long,
        @RequestBody replyRequest: ReplyRequest,
        @AuthenticationPrincipal principal: MemberPrincipal
    ): ResponseEntity<DefaultResponse> =
        ResponseEntity.status(HttpStatus.OK).body(communityCommentService.updateReply(replyRequest, id, principal.id))

    @Operation(summary = "커뮤니티 댓글 삭제 API", description = "커뮤니티 댓글 id 값을 넣어 주시면 됩니다")
    @PreAuthorize("hasRole('CONSUMER')")
    @DeleteMapping("/{id}")
    override fun deleteReply(
        @PathVariable id: Long,
        @AuthenticationPrincipal principal: MemberPrincipal
    ): ResponseEntity<DefaultResponse> = ResponseEntity.status(HttpStatus.OK).body(communityCommentService.deleteReply(id, principal.id))

    @Operation(summary = "커뮤니티 댓글 신고 API", description = "커뮤니티 댓글 id 값을 넣어 주시면 됩니다")
    @PreAuthorize("hasRole('CONSUMER')")
    @PatchMapping("/report/{id}")
    override fun reportReply(
        @PathVariable id: Long,
        @AuthenticationPrincipal principal: MemberPrincipal
    ): ResponseEntity<DefaultResponse> = ResponseEntity.status(HttpStatus.OK).body(communityCommentService.reportReply(id, principal.id))
}