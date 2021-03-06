package com.example.news_feeds.data.repository

import com.example.news_feeds.data.network.NetworkServices
import javax.inject.Inject

class ArticlesRepository @Inject constructor(private val networkServices: NetworkServices) :
    ArticlesRepositoryInterface {

    override suspend fun getWebArticles() =
        networkServices.getWebArticles()


    override suspend fun getPressArticles() =
        networkServices.getPressArticles()


}
