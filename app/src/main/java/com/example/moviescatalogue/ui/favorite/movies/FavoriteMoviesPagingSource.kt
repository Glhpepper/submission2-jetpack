package com.example.moviescatalogue.ui.favorite.movies

import androidx.paging.PagingSource
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.room.CatalogueDatabase
import com.example.moviescatalogue.di.module.DispatcherModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class FavoriteMoviesPagingSource(private val ioDispatcher: CoroutineDispatcher, private val catalogueDatabase: CatalogueDatabase) :
    PagingSource<Int, FavoriteMovies>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FavoriteMovies> {
        val moviesDao = catalogueDatabase.favoriteMovieDao()
        return withContext(ioDispatcher) {
             try {
                val movieList = moviesDao.getFavoriteMoviesPaging()
                LoadResult.Page(
                    data = movieList,
                    prevKey = null,
                    nextKey = null
                )
            } catch (exception: IOException) {
                LoadResult.Error(exception)
            } catch (exception: HttpException) {
                LoadResult.Error(exception)
            }
        }
    }
}