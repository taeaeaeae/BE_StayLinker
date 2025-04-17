package com.yoong.sunnyside.domain.real_estate.real_estate_attribute.entity

import com.yoong.sunnyside.domain.real_estate.dto.CreateRealEstate
import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import com.yoong.sunnyside.domain.real_estate.enum_class.DataType
import com.yoong.sunnyside.domain.real_estate.enum_class.RealEstateAttributeData
import com.yoong.sunnyside.domain.real_estate.real_estate_attribute.dto.AttributeValueSet
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "real_estate_attribute")
class RealEstateAttribute(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "real_estate_id", nullable = false)
    var realEstate: RealEstate,

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

    companion object {
        fun from(realEstate: RealEstate, name: RealEstateAttributeData, value: Any): RealEstateAttribute {
            val casted = AttributeValueSet.castAttributeValue(name.dataType, value)
            return RealEstateAttribute(
                realEstate = realEstate,
                name = name,
                dataType = name.dataType,
                valueString = casted.valueString,
                valueInt = casted.valueInt,
                valueDouble = casted.valueDouble,
                valueDate = casted.valueDate,
                valueBoolean = casted.valueBoolean
            )
        }
    }

}