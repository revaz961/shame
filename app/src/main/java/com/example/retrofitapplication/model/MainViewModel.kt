package com.example.retrofitapplication.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitapplication.retrofit.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val newsLiveData = MutableLiveData<List<NewsModel>>().apply {
        mutableListOf<NewsModel>()
    }
    val _newsLiveData = newsLiveData

    fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            getNews()
        }
    }

    private suspend fun getNews() {
        val result = RetrofitService.retrofitService().getNews()
        val news = result.body()
        if (result.isSuccessful && news != null) {
            newsLiveData.postValue(news)
        }
    }
}