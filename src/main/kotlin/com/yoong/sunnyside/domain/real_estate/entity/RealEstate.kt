package com.yoong.sunnyside.domain.real_estate.entity

import com.yoong.sunnyside.domain.real_estate.enum_class.GoodsType
import com.yoong.sunnyside.domain.real_estate.enum_class.HouseType
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "real_estate")
class RealEstate(

    @Column(name="business_id", nullable = false)
    val businessId: Long?,

    @Column(name="name", nullable = false)
    val name: String,

    @Column(name="address", nullable = false)
    val address: String,

    @Column(name="completion_date", nullable = false)
    val completionDate: LocalDateTime,

    @Enumerated(EnumType.STRING)
    @Column(name="house_type", nullable = false)
    val houseType: HouseType,

    @Enumerated(EnumType.STRING)
    @Column(name = "goods_type", nullable = false)
    val goodsType: GoodsType,

    @Column(name="security", nullable = false)
    val security: Int,

    @Column(name="rent", nullable = false)
    val rent: Int,

    @Column(name="house_size", nullable = false)
    val houseSize: Double,

    @Column(name="maintenance_cost", nullable = false)
    val maintenanceCost: Int,

    @Column(name="room_count", nullable = false)
    val roomCount: Int,

    @Column(name="floor", nullable = false)
    val floor: Int,

    @Column(name="contract_period", nullable = false)
    val contractPeriod: LocalDateTime,

    @Column(name="description", nullable = false)
    val description: String,

    @Column(name="bathroom_count", nullable = false)
    val bathroomCount: Int,

    @Column(name="is_parked", nullable = false)
    val isParked: Boolean,

    @Column(name="rate", nullable = false)
    val rate: Double,
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
    val deletedAt: LocalDateTime? = null
}