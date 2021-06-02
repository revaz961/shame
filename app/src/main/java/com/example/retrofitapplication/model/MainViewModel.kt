package com.example.retrofitapplication.model

import android.util.Log.d
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.retrofitapplication.ExamplePagingSource
import com.example.retrofitapplication.retrofit.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val flow = Pager(PagingConfig(pageSize = 20)){
        ExamplePagingSource(RetrofitService())
    }.liveData.cachedIn(viewModelScope)

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
        val result = RetrofitService().retrofitService().getNews()
        val news = result.body()
        if (result.isSuccessful && news != null) {
            newsLiveData.postValue(news)
            d("News",result.body().toString())
        }
    }
}