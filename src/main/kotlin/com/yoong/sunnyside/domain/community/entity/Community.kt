package com.yoong.sunnyside.domain.community.entity

import com.yoong.sunnyside.domain.community.dto.CommunityRequest
import com.yoong.sunnyside.domain.community.enum_class.CommunityType
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "community")
class Community(

    @Column(name = "consumer_id", nullable = false)
    val consumerId: Long,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "description", nullable = false)
    var description: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "community_type", nullable = false)
    var communityType: CommunityType,

){

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "deleted_at", nullable = true)
    var deletedAt: LocalDateTime? = null

    constructor(communityRequest : CommunityRequest, consumerId: Long): this(
        title = communityRequest.title,
        description = communityRequest.description,
        communityType = communityRequest.communityType,
        consumerId = consumerId,
    )

    fun update(communityRequest: CommunityRequest) {
        this.title = communityRequest.title
        this.description = communityRequest.description
        this.communityType = communityRequest.communityType
    }

    fun delete() {
        this.deletedAt = LocalDateTime.now()
    }
}