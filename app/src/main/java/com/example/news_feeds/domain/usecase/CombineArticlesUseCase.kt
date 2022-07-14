package com.example.news_feeds.domain.usecase

import com.example.example.Article
import com.example.news_feeds.data.repository.ArticlesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import javax.inject.Inject


class CombineArticlesUseCase @Inject constructor(private val articlesRepository: ArticlesRepository) {
    private val webArticles = CoroutineScope(Dispatchers.IO).async {
        articlesRepository.getWebArticles().body()?.articles
    }

    private val pressArticles = CoroutineScope(Dispatchers.IO).async {
        articlesRepository.getPressArticles().body()?.articles
    }

    suspend fun getArticles(): List<Article>? {
        val allArticles = awaitAll(webArticles, pressArticles)

        return if (allArticles.all { !it.isNullOrEmpty() }) {
            ((allArticles[0])?.plus((allArticles[1]) as ArrayList<Article>))
        } else
            arrayListOf()
    }

}