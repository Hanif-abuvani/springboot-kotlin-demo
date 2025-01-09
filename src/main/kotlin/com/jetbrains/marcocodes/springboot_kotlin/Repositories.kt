package com.jetbrains.marcocodes.springboot_kotlin

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ArticleRepository : JpaRepository<Article, Long>{
    fun findAllByOrderByCreatedAtDesc(): Iterable<Article>

    fun findBySlug(slug: String): Optional<Article>

    fun findAllBySlug(slug: String): Iterable<Article>

}