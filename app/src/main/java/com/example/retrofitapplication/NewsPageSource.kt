package com.example.retrofitapplication

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.retrofitapplication.model.NewsModel
import com.example.retrofitapplication.retrofit.RetrofitService
import retrofit2.HttpException

class NewsPageSource : PagingSource<Int, NewsModel>() {
    override fun getRefreshKey(state: PagingState<Int, NewsModel>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsModel> {
        var pageNumber = params.key?: INITIAL_PAGE_NUMBER
        return try {
            val response = RetrofitService.retrofitService().getNews()
            val news = response.body()!!
            val nextPageNumber = if (news.isEmpty()) null else pageNumber + 1
            val prevPageNumber =  if (pageNumber > 1) pageNumber - 1 else null
            LoadResult.Page(news, prevPageNumber, nextPageNumber)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 1
    }
}