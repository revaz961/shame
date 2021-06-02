package com.example.retrofitapplication.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    companion object {

        private const val BASE_URL = "http://139.162.207.17/"
        private val gson = GsonBuilder()
            .setLenient()
            .create();

        fun retrofitService(): RetrofitApi {
            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)).build()
                .create(RetrofitApi::class.java)
        }

    }
}