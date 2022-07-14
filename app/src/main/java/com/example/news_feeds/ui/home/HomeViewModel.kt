package com.example.news_feeds.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.Article
import com.example.news_feeds.domain.usecase.CombineArticlesUseCase
import com.example.news_feeds.domain.util.InternetConnectivity
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val articlesUseCase: CombineArticlesUseCase,
    @ApplicationContext context: Context,
) :
    ViewModel() {

    private val _articlesList = MutableLiveData<List<Article>>()
    val articlesList: LiveData<List<Article>> = _articlesList


    suspend fun getArticles() =
        _articlesList.postValue(articlesUseCase.getArticles())

    val connectionLiveData = InternetConnectivity(context)

}