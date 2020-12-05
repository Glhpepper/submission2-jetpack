package com.example.moviescatalogue.ui.tvshows

import androidx.paging.PagingSource
import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.example.moviescatalogue.data.remote.ApiServices
import retrofit2.HttpException
import java.io.IOException

class TvShowsPagingSource(private val network: ApiServices) : PagingSource<Int, TvShowsEntity>() {

    companion object {
        private const val FIRST_PAGE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShowsEntity> {
        val page = params.key ?: FIRST_PAGE
        return try {
            val response = network.getListTvShows(page = page)
            val movieList = response.results
            LoadResult.Page(
                data = movieList,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = if (movieList.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}