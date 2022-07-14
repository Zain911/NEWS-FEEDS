package com.example.news_feeds.di

import com.example.news_feeds.BuildConfig.BaseUrl
import com.example.news_feeds.data.network.AuthInterceptor
import com.example.news_feeds.data.network.NetworkServices
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideArticlesService(): NetworkServices {
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(AuthInterceptor()).build()
            )
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(NetworkServices::class.java)
    }
}