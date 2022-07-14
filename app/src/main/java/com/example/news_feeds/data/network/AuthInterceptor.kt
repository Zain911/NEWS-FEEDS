package com.example.weatherphoto.data.network


import com.example.news_feeds.BuildConfig
import okhttp3.Interceptor

class AuthInterceptor : Interceptor {


    private val queryAppIdText = "apiKey"

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val originalRequest = chain.request()

        val originalHttpUrl = originalRequest.url()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(queryAppIdText, BuildConfig.apiKey)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }


}