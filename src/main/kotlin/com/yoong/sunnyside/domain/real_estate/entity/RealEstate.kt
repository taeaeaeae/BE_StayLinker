package com.yoong.sunnyside.domain.real_estate.entity

import com.yoong.sunnyside.domain.real_estate.dto.CreateRealEstate
import com.yoong.sunnyside.domain.real_estate.dto.UpdateRealEstate
import com.yoong.sunnyside.domain.real_estate.enum_class.GoodsType
import com.yoong.sunnyside.domain.real_estate.enum_class.HouseType
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SQLRestriction
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@SQLRestriction("deleted_at is null")
@Table(name = "real_estate")
class RealEstate(

    @Column(name="business_id", nullable = false)
    val businessId: Long?,

    @Column(name="name", nullable = false)
    var name: String,

    @Column(name="description", nullable = false)
    var description: String,

    @Column(name="rate", nullable = false)
    var rate: Double,

    @Column(name="price", nullable = false)
    var price: Long,

    @Column(name="latitude", nullable = false)
    var latitude: Double,

    @Column(name="longitude", nullable = false)
    var longitude: Double,

    ){

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name="created_at", nullable = false)
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name="updated_at", nullable = false)
    @UpdateTimestamp
    val updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name="deleted_at", nullable = true)
    var deletedAt: LocalDateTime? = null

    fun update(updateRealEstate: UpdateRealEstate) {
        this.name = updateRealEstate.name
        this.description = updateRealEstate.description
    }

    fun delete() {
        this.deletedAt = LocalDateTime.now()
    }

    constructor(id: Long, createRealEstate: CreateRealEstate): this(
        businessId = id,
        name = createRealEstate.name,
        description = createRealEstate.description,
        rate = 0.0,
        price = createRealEstate.price,
        latitude = createRealEstate.latitude,
        longitude = createRealEstate.longitude
    )

}