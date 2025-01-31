package com.yoong.sunnyside.infra.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.yoong.sunnyside.common.dto.ErrorResponse
import com.yoong.sunnyside.common.exception.InternalServerException
import com.yoong.sunnyside.common.exception.handler.GlobalExceptionHandler
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class CustomInternalServerErrorHandler: OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {

        kotlin.runCatching{
            filterChain.doFilter(request, response)
        }.onFailure {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            response.contentType = "application/json"
            val objectMapper = ObjectMapper()
            val jsonString = objectMapper.writeValueAsString(ErrorResponse(500, "Internal Server Error"))
            response.writer.write(jsonString)
        }
    }
}