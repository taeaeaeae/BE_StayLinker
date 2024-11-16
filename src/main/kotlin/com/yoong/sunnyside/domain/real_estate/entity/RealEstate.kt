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

    @Column(name="address", nullable = false)
    var address: String,

    @Column(name="price", nullable = false)
    var price: Long,

    @Column(name="completion_date", nullable = false)
    var completionDate: LocalDateTime,

    @Enumerated(EnumType.STRING)
    @Column(name="house_type", nullable = false)
    var houseType: HouseType,

    @Enumerated(EnumType.STRING)
    @Column(name = "goods_type", nullable = false)
    var goodsType: GoodsType,

    @Column(name="security", nullable = false)
    var security: Int,

    @Column(name="rent", nullable = false)
    var rent: Int,

    @Column(name="house_size", nullable = false)
    var houseSize: Double,

    @Column(name="maintenance_cost", nullable = false)
    var maintenanceCost: Int,

    @Column(name="room_count", nullable = false)
    var roomCount: Int,

    @Column(name="floor", nullable = false)
    var floor: Int,

    @Column(name="contract_period", nullable = false)
    var contractPeriod: LocalDateTime,

    @Column(name="description", nullable = false)
    var description: String,

    @Column(name="bathroom_count", nullable = false)
    var bathroomCount: Int,

    @Column(name="is_parked", nullable = false)
    var isParked: Boolean,

    @Column(name="rate", nullable = false)
    var rate: Double,

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
        this.address = updateRealEstate.address
        this.completionDate = updateRealEstate.completionDate
        this.houseType = updateRealEstate.houseType
        this.goodsType = updateRealEstate.goodsType
        this.security = updateRealEstate.security
        this.rent = updateRealEstate.rent
        this.houseSize = updateRealEstate.size
        this.maintenanceCost = updateRealEstate.maintenanceCost
        this.roomCount = updateRealEstate.roomCount
        this.floor = updateRealEstate.floor
        this.contractPeriod = updateRealEstate.contractPeriod
        this.description = updateRealEstate.description
        this.bathroomCount = updateRealEstate.bathroomCount
        this.isParked = updateRealEstate.isParked
    }

    fun delete() {
        this.deletedAt = LocalDateTime.now()
    }

    constructor(businessId: Long, createRealEstate: CreateRealEstate) : this(
        businessId = businessId,
        price = createRealEstate.price,
        name = createRealEstate.name,
        address = createRealEstate.address,
        completionDate = createRealEstate.completionDate,
        houseType = createRealEstate.houseType,
        goodsType = createRealEstate.goodsType,
        security = createRealEstate.security,
        rent = createRealEstate.rent,
        houseSize = createRealEstate.size,
        maintenanceCost = createRealEstate.maintenanceCost,
        roomCount = createRealEstate.roomCount,
        floor = createRealEstate.floor,
        contractPeriod = createRealEstate.contractPeriod,
        description = createRealEstate.description,
        bathroomCount = createRealEstate.bathroomCount,
        isParked = createRealEstate.isParked,
        rate = 0.0,
    )

}