package com.dialiax.newsapp.Screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dialiax.newsapp.network.NewsModel
import com.dialiax.newsapp.repo.Repo
import kotlinx.coroutines.launch

class NewsViewModel() : ViewModel() {
    var res = mutableStateOf<NewsModel?>(null)
    var isLoading = mutableStateOf(false) // Add this line

    init {
        refreshNews()
    }

    suspend fun getNews(repo: Repo): NewsModel? {
        return repo.newProvider().body()
    }

    fun refreshNews(){
        viewModelScope.launch {
            isLoading.value = true // Set isLoading to true before fetching the news
            res.value = getNews(Repo())
            isLoading.value = false // Set isLoading to false after fetching the news
        }
    }
}