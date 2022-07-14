package com.example.news_feeds.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news_feeds.domain.CombineArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val articlesUseCase: CombineArticlesUseCase) : ViewModel() {

   suspend fun getArticles()=
        articlesUseCase.getArticles()

}