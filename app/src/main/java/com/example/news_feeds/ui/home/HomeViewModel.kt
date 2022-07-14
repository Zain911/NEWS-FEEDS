package com.example.news_feeds.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.Articles
import com.example.news_feeds.domain.CombineArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val articlesUseCase: CombineArticlesUseCase) :
    ViewModel() {

    private val _articlesList = MutableLiveData<List<Articles>>()
    val articlesList: LiveData<List<Articles>> = _articlesList


    suspend fun getArticles() {
        _articlesList.value = articlesUseCase.getArticles()
        //articlesList.postValue(articlesUseCase.getArticles())

    }
}