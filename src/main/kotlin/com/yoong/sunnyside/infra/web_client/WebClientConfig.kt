package com.yoong.sunnyside.infra.web_client

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.web.reactive.function.client.WebClient


@Configuration
class WebClientConfig(
    @Value("\${api.auth.token}")
    private val token: String,
){

    @Bean
    fun connect(): WebClient {
        return WebClient.builder()
            .defaultHeaders{
                it.add(HttpHeaders.CONTENT_TYPE, "application/json")
                it.add(HttpHeaders.ACCEPT_CHARSET, "utf-8")
                it.add("Authorization", "Token $token")
            }.build()
    }
}