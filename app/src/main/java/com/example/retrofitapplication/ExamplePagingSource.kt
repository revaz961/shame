package com.example.retrofitapplication

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.retrofitapplication.model.NewsModel
import com.example.retrofitapplication.retrofit.RetrofitService
import retrofit2.HttpException

class ExamplePagingSource(
    val backend: RetrofitService
) : PagingSource<Int, NewsModel>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, NewsModel> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = backend.retrofitService().getNews()
            val news = response.body()!!.toList()
            return LoadResult.Page(news,null,null)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}