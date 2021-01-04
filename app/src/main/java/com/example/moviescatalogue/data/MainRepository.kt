package com.example.moviescatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.entity.FavoriteShows
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.example.moviescatalogue.data.local.room.CatalogueDatabase
import com.example.moviescatalogue.data.local.room.FavoriteMoviesDao
import com.example.moviescatalogue.data.remote.ApiServices
import com.example.moviescatalogue.data.remote.response.ResponseDetailMovies
import com.example.moviescatalogue.data.remote.response.ResponseDetailShows
import com.example.moviescatalogue.di.module.DispatcherModule.IoDispatcher
import com.example.moviescatalogue.ui.favorite.movies.FavoriteMoviesPagingSource
import com.example.moviescatalogue.ui.favorite.tvshows.FavoriteShowsPagingSource
import com.example.moviescatalogue.ui.main.movies.MoviePagingSource
import com.example.moviescatalogue.ui.main.tvshows.TvShowsPagingSource
import com.example.moviescatalogue.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val network: ApiServices,
    private val catalogueDatabase: CatalogueDatabase
) : MainDataSource {
    private val moviesDao = catalogueDatabase.favoriteMovieDao()
    private val showsDao = catalogueDatabase.favoriteShowsDao()

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }

    override suspend fun getMoviesApi(): LiveData<PagingData<MoviesEntity>> {
        wrapEspressoIdlingResource {
            return Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { MoviePagingSource(network) }
            ).liveData
        }
    }

    override suspend fun getShowsApi(): LiveData<PagingData<TvShowsEntity>> {
        wrapEspressoIdlingResource {
            return Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { TvShowsPagingSource(network) }
            ).liveData
        }
    }

    override suspend fun getDetailMovie(id: String): LiveData<ResponseDetailMovies> {
        wrapEspressoIdlingResource {
            val moviesDetailResults = MutableLiveData<ResponseDetailMovies>()
            withContext(ioDispatcher) {
                val moviesDetail = network.getDetailMovies(id)
                moviesDetailResults.postValue(moviesDetail)
            }
            return moviesDetailResults
        }
    }

    override suspend fun getDetailTvShows(id: String): LiveData<ResponseDetailShows> {
        wrapEspressoIdlingResource {
            val tvShowsDetailResults = MutableLiveData<ResponseDetailShows>()
            withContext(ioDispatcher) {
                val showsDetail = network.getDetailTvShows(id)
                tvShowsDetailResults.postValue(showsDetail)
            }
            return tvShowsDetailResults
        }
    }

    override suspend fun getMoviesFavoritePaging(): LiveData<PagingData<FavoriteMovies>> {
        wrapEspressoIdlingResource {
            return Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = {
                    FavoriteMoviesPagingSource(
                        ioDispatcher,
                        catalogueDatabase
                    )
                }
            ).liveData
        }
    }

    override suspend fun checkFavoriteMovies(id: Int): Boolean {
        return withContext(ioDispatcher) {
            moviesDao.checkFavorite(id)
        }
    }

    override suspend fun insertFavoriteMovies(favoriteMovies: FavoriteMovies) {
        withContext(ioDispatcher) {
            moviesDao.insertFavoriteMovies(favoriteMovies)
        }
    }

    override suspend fun deleteFavoriteMoviesById(id: Int) {
        withContext(ioDispatcher) {
            moviesDao.deleteFavoriteById(id)
        }
    }

    override suspend fun deleteFavoriteMovies(favoriteMovies: FavoriteMovies) {
        withContext(ioDispatcher) {
            moviesDao.deleteFavoriteUser(favoriteMovies)
        }
    }

    override suspend fun getShowsFavoritePaging(): LiveData<PagingData<FavoriteShows>> {
        wrapEspressoIdlingResource {
            return Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = {
                    FavoriteShowsPagingSource(
                        ioDispatcher,
                        catalogueDatabase
                    )
                }
            ).liveData
        }
    }

    override suspend fun checkFavoriteShows(id: Int): Boolean {
        return withContext(ioDispatcher) {
            showsDao.checkFavorite(id)
        }
    }

    override suspend fun insertFavoriteShows(favoriteShows: FavoriteShows) {
        withContext(ioDispatcher) {
            showsDao.insertFavoriteShows(favoriteShows)
        }
    }

    override suspend fun deleteFavoriteShowsById(id: Int) {
        withContext(ioDispatcher) {
            showsDao.deleteFavoriteById(id)
        }
    }

    override suspend fun deleteFavoriteShows(favoriteShows: FavoriteShows) {
        withContext(ioDispatcher) {
            showsDao.deleteFavoriteUser(favoriteShows)
        }
    }
}