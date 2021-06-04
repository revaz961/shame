package com.example.retrofitapplication.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.retrofitapplication.NewsPageSource
import com.example.retrofitapplication.retrofit.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    fun getNews(): LiveData<PagingData<NewsModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { NewsPageSource() }
        ).liveData
    }

//    private val _newsLiveData = MutableLiveData<Response<List<NewsModel>>>().apply {
//        mutableListOf<NewsModel>()
//    }
//    val newsLiveData = _newsLiveData

//    fun init() {
//        CoroutineScope(Dispatchers.IO).launch {
//            newsLiveData.postValue(Response.Loading(true))
//            getNews()
//            newsLiveData.postValue(Response.Loading(false))
//        }
//    }

//    private suspend fun getNews() {
//
//        val result = RetrofitService.retrofitService().getNews()
//        val news = result.body()
//        if (result.isSuccessful && news != null) {
//            newsLiveData.postValue(Response.Success(news))
//        }else{
//            newsLiveData.postValue(Response.Error(news!!,result.errorBody().toString()))
//        }
//    }
}