package com.jetbrains.marcocodes.springboot_kotlin

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/articles")
class ArticleControllers(val  repository: ArticleRepository) {

    //val articles = mutableListOf(Article("My Title", "My content"))

    @GetMapping
    //fun articles() = articles
    fun articles() = repository.findAllByOrderByCreatedAtDesc()

    @GetMapping("/{slug}")
    fun article(@PathVariable slug: String) =
        /* articles.find { article -> article.slug == slug } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)*/
        repository.findBySlug(slug).orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND) }


    @PostMapping
    fun newArticle(@RequestBody article: Article): Article{
        //articles.add(article)
        article.id = null
        repository.save(article)
        return article
    }

    @PutMapping("/{slug}")
    fun updateArticle(@RequestBody article: Article, @PathVariable slug: String ): Article{
        //val existingArticle = articles.find { it.slug == slug } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        val existingArticle = repository.findBySlug(slug).orElseThrow{ throw ResponseStatusException(HttpStatus.NOT_FOUND) }
        existingArticle.content = article.content
        repository.save(existingArticle)
        return existingArticle
    }

    @DeleteMapping("/{slug}")
    fun deleteArticle(@PathVariable slug: String){
        //articles.removeIf { article -> article.slug == slug }
        val existingArticle = repository.findBySlug(slug).orElseThrow{ throw ResponseStatusException(HttpStatus.NOT_FOUND) }
        repository.delete(existingArticle)
    }

    @DeleteMapping("/all/{slug}")
    fun deleteMultipleArticle(@PathVariable slug: String){
        //articles.removeIf { article -> article.slug == slug }
        val existingArticle = repository.findAllBySlug(slug)//.orElseThrow{ throw ResponseStatusException(HttpStatus.NOT_FOUND) }
        existingArticle.forEach {
            repository.delete(it)
        }
    }


}