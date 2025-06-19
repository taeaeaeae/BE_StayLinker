package com.yoong.sunnyside.domain.real_estate.real_estate_attribute.dto

import com.yoong.sunnyside.domain.real_estate.enum_class.DataType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class AttributeValueSet(
    val valueString: String? = null,
    val valueInt: Int? = null,
    val valueDouble: Double? = null,
    val valueDate: LocalDateTime? = null,
    val valueBoolean: Boolean? = null,
){
    companion object{
        fun castAttributeValue(dataType: DataType, data: Any): AttributeValueSet {
            return when(dataType){
                DataType.STRING -> AttributeValueSet(valueString = data.toString())
                DataType.INT -> AttributeValueSet(valueInt = data.toString().toInt())
                DataType.DOUBLE -> AttributeValueSet(valueDouble = data.toString().toDouble())
                DataType.DATE -> AttributeValueSet(valueDate = LocalDateTime.parse(data.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                DataType.BOOLEAN -> AttributeValueSet(valueBoolean = data.toString().toBoolean())
            }
        }
    }
}
