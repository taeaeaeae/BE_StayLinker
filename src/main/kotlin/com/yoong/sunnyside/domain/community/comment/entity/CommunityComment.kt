package com.yoong.sunnyside.domain.community.comment.entity

import com.yoong.sunnyside.domain.community.entity.Community
import com.yoong.sunnyside.domain.consumer.entity.Consumer
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "community_comment")
class CommunityComment(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id", nullable = false)
    val community: Community,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id", nullable = false)
    val consumer: Consumer,

    val description: String,

    ) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "created_at",nullable = false)
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.now()
}