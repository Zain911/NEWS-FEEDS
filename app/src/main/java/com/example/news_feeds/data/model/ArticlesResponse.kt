package com.example.news_feeds.data.model

import com.example.example.Articles
import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    @SerializedName("status") var status: String? = null,
    @SerializedName("source") var source: String? = null,
    @SerializedName("sortBy") var sortBy: String? = null,
    @SerializedName("articles") var articles: ArrayList<Articles> = arrayListOf(),
)
