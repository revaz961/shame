package com.example.retrofitapplication.retrofit

import com.example.retrofitapplication.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {
    @GET("/api/m/v2/news")
    suspend fun getNews(): Response<List<NewsModel>>
}