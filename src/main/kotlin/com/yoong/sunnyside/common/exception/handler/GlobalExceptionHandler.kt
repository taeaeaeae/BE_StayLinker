package com.yoong.sunnyside.common.exception.handler

import com.yoong.sunnyside.common.dto.ErrorResponse
import com.yoong.sunnyside.common.exception.AccessDeniedException
import com.yoong.sunnyside.common.exception.CustomIllegalArgumentException
import com.yoong.sunnyside.common.exception.ModelNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException::class)
    fun accessDeniedException(e: AccessDeniedException): ResponseEntity<ErrorResponse>
        = ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorResponse(403, e.message!!))

    @ExceptionHandler(ModelNotFoundException::class)
    fun modelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorResponse>
            = ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse(404, e.message!!))

    @ExceptionHandler(CustomIllegalArgumentException::class)
    fun customIllegalArgumentException(e: AccessDeniedException): ResponseEntity<ErrorResponse>
            = ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse(409, e.message!!))
}