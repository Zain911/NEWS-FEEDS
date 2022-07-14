package com.example.news_feeds.data.repository

import com.example.news_feeds.data.model.ArticlesResponse
import retrofit2.Response

interface ArticlesRepositoryInterface {
    suspend fun getWebArticles(): Response<ArticlesResponse>
    suspend fun getPressArticles(): Response<ArticlesResponse>
}
