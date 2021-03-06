package com.example.news_feeds.data.network

import com.example.news_feeds.data.model.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkServices {

    @GET("articles?source=the-next-web")
    suspend fun getWebArticles(
    ): Response<ArticlesResponse>

    @GET("articles?source=associated-press")
    suspend fun getPressArticles(): Response<ArticlesResponse>
}