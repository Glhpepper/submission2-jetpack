package com.example.moviescatalogue.ui.favorite.tvshows

import androidx.paging.PagingSource
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.entity.FavoriteShows
import com.example.moviescatalogue.data.local.room.CatalogueDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class FavoriteShowsPagingSource(
    private val ioDispatcher: CoroutineDispatcher,
    private val catalogueDatabase: CatalogueDatabase
) :
    PagingSource<Int, FavoriteShows>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FavoriteShows> {
        val moviesDao = catalogueDatabase.favoriteShowsDao()
        return withContext(ioDispatcher) {
            try {
                val showsList = moviesDao.getFavoriteShowsPaging()
                LoadResult.Page(
                    data = showsList,
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