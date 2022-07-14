package com.example.news_feeds.data.repository

import com.example.weatherphoto.data.network.NetworkServices
import javax.inject.Inject

class ArticlesRepository @Inject constructor(private val networkServices: NetworkServices) :
    ArticlesRepositoryInterface {

    override suspend fun getWebArticles() =
        networkServices.getArticlesWeb()


    override suspend fun getPressArticles() =
        networkServices.getArticlePress()


}
