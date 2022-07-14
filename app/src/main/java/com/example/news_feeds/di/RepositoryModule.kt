package com.example.news_feeds.di


import com.example.news_feeds.data.repository.ArticlesRepository
import com.example.news_feeds.data.network.NetworkServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideArticlesRepository(networkServices: NetworkServices) : ArticlesRepository {
        return ArticlesRepository(networkServices)
    }

}