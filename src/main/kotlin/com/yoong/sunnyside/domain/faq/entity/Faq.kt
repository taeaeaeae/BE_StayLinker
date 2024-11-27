package com.yoong.sunnyside.domain.faq.entity

import com.yoong.sunnyside.domain.faq.dto.CreateFaqRequest
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "faq")
class Faq(
    @Column(name = "question", nullable = false)
    var question: String,

    @Column(name = "answer", nullable = false)
    var answer: String,

    @Column(name = "division", nullable = false)
    var division: String,
) {

    constructor(request: CreateFaqRequest) : this(
        question = request.question,
        answer = request.answer,
        division = request.division
    )

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null


    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "deleted_at", nullable = true)
    var deletedAt: LocalDateTime? = null
}