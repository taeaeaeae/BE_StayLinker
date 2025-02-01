package com.yoong.sunnyside.infra.web_client.hikorea

import com.yoong.sunnyside.domain.consumer.dto.AlienRegistrationCardRequest
import com.yoong.sunnyside.infra.encrypt.utils.AESUtil
import com.yoong.sunnyside.infra.web_client.WebClientConfig
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters

@Component
class HiKoreaClient(
    private val webClient: WebClientConfig,
    private val aesUtil: AESUtil
){

    val log = LoggerFactory.getLogger("map Log")
    fun request(alienRegistrationCardRequest: AlienRegistrationCardRequest): Map<String, Any>{

        val alienRegistrationCardRequestMap = HashMap<String, String>()

        alienRegistrationCardRequestMap["FOREIGNJUMIN"] = aesUtil.encrypt(alienRegistrationCardRequest.foreignJumin)
        alienRegistrationCardRequestMap["ISSUEDATE"] = aesUtil.encrypt(alienRegistrationCardRequest.issueDate)
        alienRegistrationCardRequestMap["SERIALNUMBER"] = aesUtil.encrypt(alienRegistrationCardRequest.serialNumber)
        log.info(alienRegistrationCardRequestMap.toString())

        return webClient.connect()
            .post()
            .uri("https://datahub-dev.scraping.co.kr/scrap/docInq/hikorea/ForeignerAuthenticity")
            .body(BodyInserters.fromValue(alienRegistrationCardRequestMap))
            .retrieve()
            .bodyToMono(object : ParameterizedTypeReference<Map<String, Any>>() {})
            .block() ?: mapOf()
    }
}