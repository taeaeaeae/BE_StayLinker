package com.yoong.sunnyside.domain.community.controller

import com.yoong.sunnyside.common.dto.DefaultResponseDto
import com.yoong.sunnyside.domain.community.dto.AllCommunityResponseDto
import com.yoong.sunnyside.domain.community.dto.CommunityRequestDto
import com.yoong.sunnyside.domain.community.dto.CommunityResponseDto
import com.yoong.sunnyside.domain.community.service.CommunityService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/community")
class CommunityController(
    private val communityService: CommunityService
){

    @PostMapping
    fun createCommunity(
        @RequestBody communityRequestDto: CommunityRequestDto
    ): ResponseEntity<DefaultResponseDto>
        = ResponseEntity.status(HttpStatus.OK).body(communityService.createCommunity(communityRequestDto))

    @GetMapping
    fun getAllCommunity(): ResponseEntity<AllCommunityResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(communityService.getAllCommunity())

    @GetMapping("/{communityId}")
    fun getCommunity(
        @PathVariable("communityId") communityId: Long,
    ): ResponseEntity<CommunityResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(communityService.getCommunity(communityId))

    @PutMapping("/{communityId}")
    fun updateCommunity(
        @PathVariable("communityId") communityId: Long,
        @RequestBody communityRequestDto: CommunityRequestDto
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(communityService.updateCommunity(communityRequestDto, communityId))

    @DeleteMapping("/{communityId}")
    fun deleteCommunity(
        @PathVariable("communityId") communityId: Long,
    ): ResponseEntity<DefaultResponseDto>
            = ResponseEntity.status(HttpStatus.OK).body(communityService.deleteCommunity(communityId))
}