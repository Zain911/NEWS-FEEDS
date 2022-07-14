package com.example.news_feeds.domain

import android.util.Log
import com.example.example.Articles
import com.example.news_feeds.data.repository.ArticlesRepository
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.merge
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
        val webList = webArticles.await()
        val pressList = pressArticles.await()
    //   var x= merge(webList.body().articles,pressList.body().articles)

        val resultArticle =
            (allArticles[0].body()?.articles?.plus(allArticles[1].body()?.articles) as List<Articles>)
        var json = Gson()
        json.toJson(resultArticle.toString())
        Log.d("getArticles: ", json.toJson(resultArticle.toString()))
        return if (allArticles.all { it.isSuccessful }) {

            ((allArticles[0].body()?.articles)?.plus((allArticles[1].body()?.articles)) as List<Articles>)
        } else
            emptyList()

    }

}