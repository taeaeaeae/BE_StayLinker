package com.yoong.sunnyside.domain.consumer.dto

import jakarta.validation.constraints.Pattern
import java.util.Base64

data class AlienRegistrationCardRequest(
    val foreignJumin: String, // 외국인 등록 번호

    @Pattern(regexp = "\\d{4}\\d{2}\\d{2}", message = "발급일자는 yyyyMMdd 형식이어야 합니다.")
    val issueDate: String, // 발급 일자
    val serialNumber: String,
){

    fun toSerialNumberEncrypt(): String {
        return Base64.getEncoder().encodeToString(this.serialNumber.toByteArray())
    }

    fun toForeignJiminEncrypt(): String{
        return Base64.getEncoder().encodeToString(this.foreignJumin.toByteArray())
    }

    fun toIssueDateEncrypt(): String{
        return Base64.getEncoder().encodeToString(this.issueDate.toByteArray())
    }
}
