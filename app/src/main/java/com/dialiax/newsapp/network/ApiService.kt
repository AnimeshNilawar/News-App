package com.dialiax.newsapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getNewsFromServer(
        @Query("country") country: String = "in",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String = "YOUR_API_KEY"
    ): Response<NewsModel>

}