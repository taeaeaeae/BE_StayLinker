package com.yoong.sunnyside.domain.real_estate.real_estate_attribute.entity

import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import com.yoong.sunnyside.domain.real_estate.enum_class.DataType
import com.yoong.sunnyside.domain.real_estate.enum_class.RealEstateAttributeData
import com.yoong.sunnyside.domain.real_estate.real_estate_attribute.dto.AttributeValueSet
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "real_estate_attribute")
class RealEstateAttribute(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "real_estate_id", nullable = false)
    val realEstate: RealEstate,

    @Column(name = "name", nullable = false)
    val name: RealEstateAttributeData,

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


    constructor(realEstateData: RealEstate, realEstateAttributeData: RealEstateAttributeData, attributeValue: Any):this(
        realEstate = realEstateData,
        name = realEstateAttributeData,
        dataType = realEstateAttributeData.dataType,
        valueString = AttributeValueSet.castAttributeValue(realEstateAttributeData.dataType, attributeValue).valueString,
        valueInt = AttributeValueSet.castAttributeValue(realEstateAttributeData.dataType, attributeValue).valueInt,
        valueDouble = AttributeValueSet.castAttributeValue(realEstateAttributeData.dataType, attributeValue).valueDouble,
        valueDate = AttributeValueSet.castAttributeValue(realEstateAttributeData.dataType, attributeValue).valueDate,
        valueBoolean= AttributeValueSet.castAttributeValue(realEstateAttributeData.dataType, attributeValue).valueBoolean,
    )

}