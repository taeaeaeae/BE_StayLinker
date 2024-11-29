package com.yoong.sunnyside.domain.real_estate_option.entity

import com.yoong.sunnyside.domain.real_estate.entity.RealEstate
import com.yoong.sunnyside.domain.real_estate_option.dto.CreateRealEstateOption
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "real_estate_option")
class RealEstateOption(

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "description", nullable = true)
    var description: String?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="real_estate_id")
    var realEstate: RealEstate,
){
    constructor(createRealEstateOption: CreateRealEstateOption, realEstate: RealEstate): this(
        name = createRealEstateOption.name,
        description = createRealEstateOption.description,
        realEstate = realEstate
    )

    fun delete() {
        deletedAt = LocalDateTime.now()
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @CreationTimestamp
    @Column(name="created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    @UpdateTimestamp
    @Column(name="updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name="deleted_at", nullable = true)
    var deletedAt: LocalDateTime? = null
}