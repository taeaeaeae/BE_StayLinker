package com.yoong.sunnyside.domain.trade.reply.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.trade.reply.dto.TradeReplyRequestDto
import com.yoong.sunnyside.domain.trade.reply.service.TradeReplyService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@Tag(name = "커뮤니티 댓글 관련 API")
@RestController
@RequestMapping("/trade/reply")
class TradeReplyController(
    private val tradeReplyService: TradeReplyService
){

    @Operation(summary = "커뮤니티 댓글 생성 API", description = "커뮤니티 id 값을 넣어 주시면 됩니다")
    @PostMapping("/{tradeId}")
    fun createReply(
        @PathVariable tradeId: Long,
        @RequestBody tradeReplyRequestDto: TradeReplyRequestDto
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(tradeReplyService.createReply(tradeReplyRequestDto, tradeId))

    @Operation(summary = "커뮤니티 댓글 수정 API", description = "커뮤니티 댓글 id 값을 넣어 주시면 됩니다")
    @PutMapping("/{replyId}")
    fun updateReply(
        @PathVariable("replyId") replyId: Long,
        @RequestBody tradeReplyRequestDto: TradeReplyRequestDto
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(tradeReplyService.updateReply(tradeReplyRequestDto, replyId))

    @Operation(summary = "커뮤니티 글 삭제 API", description = "커뮤니티 댓글 id 값을 넣어 주시면 됩니다")
    @DeleteMapping("/{replyId}")
    fun deletetrade(
        @PathVariable("replyId") replyId: Long,
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(tradeReplyService.deleteReply(replyId))
}