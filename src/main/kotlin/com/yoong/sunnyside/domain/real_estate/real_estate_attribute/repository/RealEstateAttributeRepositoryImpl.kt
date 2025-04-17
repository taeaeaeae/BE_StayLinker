package com.yoong.sunnyside.domain.real_estate.real_estate_attribute.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.yoong.sunnyside.domain.real_estate.entity.QRealEstate
import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
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
        TODO("Not yet implemented")
    }
}