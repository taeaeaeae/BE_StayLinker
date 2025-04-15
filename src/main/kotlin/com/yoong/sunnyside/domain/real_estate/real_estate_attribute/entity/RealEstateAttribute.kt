package com.yoong.sunnyside.domain.real_estate.real_estate_attribute.entity

import com.yoong.sunnyside.common.config.TypeCheck
import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import com.yoong.sunnyside.domain.real_estate.enum_class.DataType
import com.yoong.sunnyside.domain.real_estate.real_estate_attribute.dto.AttributeValueSet
import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity
@Table(name = "real_estate_attribute")
class RealEstateAttribute(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "real_estate_id", nullable = false)
    val realEstate: RealEstate,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "data_type", nullable = false)
    @Enumerated(EnumType.STRING)
    val dataType: DataType,

    @Column(name = "value_string", nullable = true)
    var valueString: String? = null,

    @Column(name = "value_int", nullable = true)
    var valueInt: Int? = null,

    @Column(name = "value_Double", nullable = true)
    var valueDouble: Double? = null,

    @Column(name = "value_date", nullable = true)
    var valueDate: LocalDateTime? = null,

    @Column(name = "value_boolean", nullable = true)
    var valueBoolean: Boolean? = null
){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null


    constructor(realEstateData: RealEstate, attributeName: String, attributeValue: Any):this(
        realEstate = realEstateData,
        name = attributeName,
        dataType = TypeCheck.realEstateAttribute(attributeName),
        valueString = AttributeValueSet.castAttributeValue(TypeCheck.realEstateAttribute(attributeName), attributeValue).valueString,
        valueInt = AttributeValueSet.castAttributeValue(TypeCheck.realEstateAttribute(attributeName), attributeValue).valueInt,
        valueDouble = AttributeValueSet.castAttributeValue(TypeCheck.realEstateAttribute(attributeName), attributeValue).valueDouble,
        valueDate = AttributeValueSet.castAttributeValue(TypeCheck.realEstateAttribute(attributeName), attributeValue).valueDate,
        valueBoolean= AttributeValueSet.castAttributeValue(TypeCheck.realEstateAttribute(attributeName), attributeValue).valueBoolean,
    )

}