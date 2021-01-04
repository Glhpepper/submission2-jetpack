package com.example.moviescatalogue.ui.main.movies

import androidx.paging.PagingSource
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.remote.ApiServices
import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(private val network: ApiServices) : PagingSource<Int, MoviesEntity>() {

    companion object {
        private const val FIRST_PAGE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesEntity> {
        val page = params.key ?: FIRST_PAGE
        return try {
            val response = network.getListMovies(page = page)
            val movieList = response.results
            LoadResult.Page(
                data = movieList,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = if (movieList.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}