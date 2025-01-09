package com.jetbrains.marcocodes.springboot_kotlin

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class Article(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val title: String?,
    var content: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val slug: String? = title?.toSlug(),
)