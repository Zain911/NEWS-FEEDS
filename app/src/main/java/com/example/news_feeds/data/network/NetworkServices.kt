package com.example.weatherphoto.data.network

import com.example.news_feeds.data.model.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkServices {

    @GET("articles?source=the-next-web")
    suspend fun getArticlesWeb(
    ): Response<ArticlesResponse>

    @GET("articles?source=associated-press")
    suspend fun getArticlePress(): Response<ArticlesResponse>
}