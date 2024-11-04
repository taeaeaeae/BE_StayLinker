package com.yoong.sunnyside.domain.trade.reply.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.reply.controller.ReplyController
import com.yoong.sunnyside.domain.reply.dto.ReplyRequestDto
import com.yoong.sunnyside.domain.reply.service.ReplyService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@Tag(name = "중고 거래 댓글 관련 API")
@RestController
@Qualifier("tradeReplyService")
@RequestMapping("/trade/reply")
class TradeReplyController(
    private val tradeReplyService: ReplyService
): ReplyController {

    @Operation(summary = "중고 거래 댓글 생성 API", description = "중고 거래 id 값을 넣어 주시면 됩니다")
    @PostMapping("/{id}")
    override fun createReply(
        @PathVariable id: Long,
        @RequestBody replyRequestDto: ReplyRequestDto
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(tradeReplyService.createReply(replyRequestDto, id))

    @Operation(summary = "중고 거래 댓글 수정 API", description = "중고 거래 댓글 id 값을 넣어 주시면 됩니다")
    @PutMapping("/{id}")
    override fun updateReply(
        @PathVariable id: Long,
        @RequestBody replyRequestDto: ReplyRequestDto
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(tradeReplyService.updateReply(replyRequestDto, id))

    @Operation(summary = "중고 거래 글 삭제 API", description = "중고 거래 댓글 id 값을 넣어 주시면 됩니다")
    @DeleteMapping("/{id}")
    override fun deleteReply(
        @PathVariable id: Long,
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(tradeReplyService.deleteReply(id))
}