package com.yoong.sunnyside.infra.openApi

import com.fasterxml.jackson.databind.ObjectMapper
import com.yoong.sunnyside.common.exception.ValidException
import com.yoong.sunnyside.domain.business.dto.BusinessResponse
import com.yoong.sunnyside.domain.business.dto.BusinessSearchResultResponse
import com.yoong.sunnyside.infra.web_client.WebClientConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder

@Service
class BusinessVerification(
    private val objectMapper: ObjectMapper,
    private val webClient: WebClientConfig,
    @Value("\${openapi.url}") private val BASE_URL: String,
    @Value("\${openapi.key}") private val API_KEY: String
) {

    fun getOfficeInfo(jurirno: String): BusinessResultResponse {
        val url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
            .queryParam("key", API_KEY)
            .queryParam("format", "json")
            .toUriString()
        val response = webClient.connect()
            .get()
            .uri("${url}&jurirno=${jurirno}")
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

        val businessRes = objectMapper.readValue(response, OfficeInfo::class.java)
        if (businessRes.EDOffices == null) throw ValidException("부동산 등록번호를 정확하게 입력했는지 확인해주세요.")

        return businessRes.EDOffices.field[0]
    }

    fun searchNameResult(keyword: String): List<BusinessSearchResultResponse> {
        val url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
            .queryParam("key", API_KEY)
            .queryParam("format", "json")
            .toUriString()
        val response = webClient.connect()
            .get()
            .uri("${url}&bsnmCmpnm=${keyword}")
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

        val businessRes = objectMapper.readValue(response, OfficeInfo::class.java)
        if (businessRes.EDOffices == null) throw ValidException("검색된 이름의 중개사무소가 존재하지 않습니다.")

        return businessRes.EDOffices.field.map { it.toResponse() }
    }


    fun searchAgentNameResult(keyword: String): List<BusinessSearchResultResponse> {
        val url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
            .queryParam("key", API_KEY)
            .queryParam("format", "json")
            .toUriString()
        val response = webClient.connect()
            .get()
            .uri("${url}&bsnmCmpnm=${keyword}")
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

        val businessRes = objectMapper.readValue(response, OfficeInfo::class.java)
        if (businessRes.EDOffices == null) throw ValidException("검색된 대표명이 존재하지 않습니다.")

        return businessRes.EDOffices.field.map { it.toResponse() }
    }


    fun searchCertificateResult(keyword: String): List<BusinessSearchResultResponse> {
        val url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
            .queryParam("key", API_KEY)
            .queryParam("format", "json")
            .toUriString()
        val response = webClient.connect()
            .get()
            .uri("${url}&jurirno=${keyword}")
            .retrieve()
            .bodyToMono(String::class.java)
            .block()

        val businessRes = objectMapper.readValue(response, OfficeInfo::class.java)
        if (businessRes.EDOffices == null) throw ValidException("검색된 중개등록번호가 존재하지 않습니다.")

        return businessRes.EDOffices.field.map { it.toResponse() }
    }


}