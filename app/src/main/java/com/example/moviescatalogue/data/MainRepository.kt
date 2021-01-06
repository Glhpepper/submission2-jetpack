package com.example.moviescatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviescatalogue.data.local.LocalDataSource
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.entity.FavoriteShows
import com.example.moviescatalogue.data.remote.RemoteDataSource
import com.example.moviescatalogue.data.remote.response.ResponseDetailMovies
import com.example.moviescatalogue.data.remote.response.ResponseDetailShows
import com.example.moviescatalogue.data.remote.response.ResponseMovies
import com.example.moviescatalogue.data.remote.response.ResponseTvShows
import com.example.moviescatalogue.di.module.DispatcherModule.IoDispatcher
import com.example.moviescatalogue.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MainDataSource {

    override suspend fun getMoviesApi(): LiveData<ResponseMovies> {
        wrapEspressoIdlingResource {
            val moviesResults = MutableLiveData<ResponseMovies>()
            withContext(ioDispatcher) {
                val moviesList = remoteDataSource.getListMovies()
                moviesResults.postValue(moviesList)
            }
            return moviesResults
        }
    }

    override suspend fun getShowsApi(): LiveData<ResponseTvShows> {
        wrapEspressoIdlingResource {
            val tvShowsResults = MutableLiveData<ResponseTvShows>()
            withContext(ioDispatcher) {
                val showsList = remoteDataSource.getListShows()
                tvShowsResults.postValue(showsList)
            }
            return tvShowsResults
        }
    }

    override suspend fun getDetailMovie(id: String): LiveData<ResponseDetailMovies> {
        wrapEspressoIdlingResource {
            val moviesDetailResults = MutableLiveData<ResponseDetailMovies>()
            withContext(ioDispatcher) {
                val moviesDetail = remoteDataSource.getDetailMovies(id)
                moviesDetailResults.postValue(moviesDetail)
            }
            return moviesDetailResults
        }
    }

    override suspend fun getDetailTvShows(id: String): LiveData<ResponseDetailShows> {
        wrapEspressoIdlingResource {
            val tvShowsDetailResults = MutableLiveData<ResponseDetailShows>()
            withContext(ioDispatcher) {
                val showsDetail = remoteDataSource.getDetailShows(id)
                tvShowsDetailResults.postValue(showsDetail)
            }
            return tvShowsDetailResults
        }
    }

    override suspend fun getMoviesFavoritePaging(): LiveData<PagedList<FavoriteMovies>> {
        wrapEspressoIdlingResource {
            val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
            return LivePagedListBuilder(localDataSource.getAllFavoriteMovies(), config).build()
        }
    }

    override suspend fun checkFavoriteMovies(id: Int): Boolean {
        wrapEspressoIdlingResource {
            return withContext(ioDispatcher) {
                localDataSource.checkFavoriteMovies(id)
            }
        }
    }

    override suspend fun insertFavoriteMovies(favoriteMovies: FavoriteMovies) {
        wrapEspressoIdlingResource {
            withContext(ioDispatcher) {
                localDataSource.insertFavoriteMovies(favoriteMovies)
            }
        }
    }

    override suspend fun deleteFavoriteMoviesById(id: Int) {
        wrapEspressoIdlingResource {
            withContext(ioDispatcher) {
                localDataSource.deleteFavoriteMoviesById(id)
            }
        }
    }

    override suspend fun deleteFavoriteMovies(favoriteMovies: FavoriteMovies) {
        wrapEspressoIdlingResource {
            withContext(ioDispatcher) {
                localDataSource.deleteFavoriteMovies(favoriteMovies)
            }
        }
    }

    override suspend fun getShowsFavoritePaging(): LiveData<PagedList<FavoriteShows>> {
        wrapEspressoIdlingResource {
            val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
            return LivePagedListBuilder(localDataSource.getAllFavoriteShows(), config).build()
        }
    }

    override suspend fun checkFavoriteShows(id: Int): Boolean {
        wrapEspressoIdlingResource {
            return withContext(ioDispatcher) {
                localDataSource.checkFavoriteShows(id)
            }
        }
    }

    override suspend fun insertFavoriteShows(favoriteShows: FavoriteShows) {
        wrapEspressoIdlingResource {
            withContext(ioDispatcher) {
                localDataSource.insertFavoriteShows(favoriteShows)
            }
        }
    }

    override suspend fun deleteFavoriteShowsById(id: Int) {
        wrapEspressoIdlingResource {
            withContext(ioDispatcher) {
                localDataSource.deleteFavoriteShowsById(id)
            }
        }
    }

    override suspend fun deleteFavoriteShows(favoriteShows: FavoriteShows) {
        wrapEspressoIdlingResource {
            withContext(ioDispatcher) {
                localDataSource.deleteFavoriteShows(favoriteShows)
            }
        }
    }
}