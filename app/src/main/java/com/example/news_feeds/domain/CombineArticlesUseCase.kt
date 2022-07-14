package com.example.news_feeds.domain

import com.example.example.Articles
import com.example.news_feeds.data.repository.ArticlesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import javax.inject.Inject


class CombineArticlesUseCase @Inject constructor(private val articlesRepository: ArticlesRepository) {
    private val webArticles = CoroutineScope(Dispatchers.IO).async {
        articlesRepository.getWebArticles()
    }
    private val pressArticles = CoroutineScope(Dispatchers.IO).async {
        articlesRepository.getPressArticles()
    }

    suspend fun getArticles(): List<Articles> {
        val allArticles = awaitAll(webArticles, pressArticles)

        return if (allArticles.all { it.isSuccessful }) {
            (allArticles[0].body()?.articles?.plus(allArticles[1].body()?.articles) as List<Articles>)
        } else
            emptyList()

    }

}