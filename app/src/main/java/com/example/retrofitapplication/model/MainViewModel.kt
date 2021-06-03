package com.example.retrofitapplication.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitapplication.retrofit.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _newsLiveData = MutableLiveData<Result<List<NewsModel>>>().apply {
        mutableListOf<NewsModel>()
    }
    val newsLiveData = _newsLiveData

    fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            newsLiveData.postValue(Result(Result.Status.LOADING))
            getNews()
            newsLiveData.postValue(Result(Result.Status.LOADING))
        }
    }

    private suspend fun getNews() {

        val result = RetrofitService.retrofitService().getNews()
        val news = result.body()
        if (result.isSuccessful && news != null) {
            newsLiveData.postValue(Result(Result.Status.SUCCESS,news))
        }else{
            newsLiveData.postValue(Result(Result.Status.ERROR,news))
        }
    }
}