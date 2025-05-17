package com.yoong.sunnyside.domain.real_estate.real_estate_attribute.repository

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.yoong.sunnyside.domain.real_estate.entity.QRealEstate
import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import com.yoong.sunnyside.domain.real_estate.enum_class.DataType
import com.yoong.sunnyside.domain.real_estate.enum_class.RealEstateAttributeData
import com.yoong.sunnyside.domain.real_estate.real_estate_attribute.entity.QRealEstateAttribute
import com.yoong.sunnyside.domain.real_estate.real_estate_attribute.entity.RealEstateAttribute
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class RealEstateAttributeRepositoryImpl(
    private val realEstateJpaRepository: RealEstateAttributeJpaRepository,
    @PersistenceContext
    private val em: EntityManager
): RealEstateAttributeRepository {

    private val queryFactory = JPAQueryFactory(em)
    private val realEstateAttribute: QRealEstateAttribute = QRealEstateAttribute.realEstateAttribute

    override fun save(realEstateAttribute: RealEstateAttribute): RealEstateAttribute {
        return realEstateJpaRepository.save(realEstateAttribute)
    }

    override fun findByAttributeData(realEstateId: Long): Map<RealEstateAttributeData, Any> {

        val query = queryFactory.selectFrom(realEstateAttribute)
            .where(
                realEstateAttribute.realEstate.id.eq(realEstateId)
                    .and(withNotNullValueOnly())
            )
            .fetch()

        return query.associate { it.name to attributeValueInsert(it.dataType, it) }
    }

    private fun withNotNullValueOnly(): BooleanExpression{

        return realEstateAttribute.valueString.isNotNull
            .or(realEstateAttribute.valueInt.isNotNull)
            .or(realEstateAttribute.valueDouble.isNotNull)
            .or(realEstateAttribute.valueDate.isNotNull)
            .or(realEstateAttribute.valueBoolean.isNotNull)
    }

    private fun attributeValueInsert(dataType: DataType, realEstateAttribute: RealEstateAttribute): Any {
        return when(dataType){
            DataType.STRING -> requireNotNull(realEstateAttribute.valueString) // null 일 경우에 예외 발생이 쉬워 진다
            DataType.INT -> requireNotNull(realEstateAttribute.valueInt)
            DataType.BOOLEAN -> requireNotNull(realEstateAttribute.valueBoolean)
            DataType.DOUBLE -> requireNotNull(realEstateAttribute.valueDouble)
            DataType.DATE -> requireNotNull(realEstateAttribute.valueDate)
        }
    }

    override fun findByRealEstateId(realEstateId: Long): List<RealEstateAttribute> {
        return realEstateJpaRepository.findByRealEstateId(realEstateId)
    }

    override fun delete(realEstateAttribute: RealEstateAttribute) {
        return realEstateJpaRepository.delete(realEstateAttribute)
    }
}