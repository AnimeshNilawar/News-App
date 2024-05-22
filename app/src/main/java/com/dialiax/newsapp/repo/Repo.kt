package com.dialiax.newsapp.repo

import com.dialiax.newsapp.network.ApiProvider
import com.dialiax.newsapp.network.NewsModel
import retrofit2.Response

class Repo() {
    suspend fun newProvider(): Response<NewsModel>{
        return ApiProvider.provideApi().getNewsFromServer()
    }
}