package com.yoong.sunnyside.infra.web_client

import io.netty.channel.ChannelOption
import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.ssl.util.InsecureTrustManagerFactory
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit
import org.springframework.http.client.reactive.ReactorClientHttpConnector


@Configuration
class WebClientConfig(
    @Value("\${api.auth.token}")
    private val token: String,
) {

    @Bean
    fun connect(): WebClient {
        val httpClient = HttpClient.create()
            .secure { it.sslContext(
                SslContextBuilder.forClient()
                    .trustManager(InsecureTrustManagerFactory.INSTANCE)
                    .build())
            }
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)  
            .responseTimeout(Duration.ofSeconds(10))
            .doOnConnected { conn ->
                conn.addHandlerLast(ReadTimeoutHandler(10, TimeUnit.SECONDS))
                    .addHandlerLast(WriteTimeoutHandler(10, TimeUnit.SECONDS))
            }

        return WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .defaultHeaders {
                it.add(HttpHeaders.CONTENT_TYPE, "application/json")
                it.add(HttpHeaders.ACCEPT_CHARSET, "utf-8")
                it.add("Authorization", "Token $token")
            }
            .build()
    }
}
