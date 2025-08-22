package com.yoong.sunnyside.infra.openApi

import com.fasterxml.jackson.annotation.JsonProperty

data class OfficeInfo(
    @JsonProperty("EDOffices")
    val EDOffices: OfficeInfoDetail? = null,
)