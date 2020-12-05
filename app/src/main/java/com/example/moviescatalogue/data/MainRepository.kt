package com.example.moviescatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.example.moviescatalogue.data.remote.ApiServices
import com.example.moviescatalogue.data.remote.response.ResponseDetailMovies
import com.example.moviescatalogue.data.remote.response.ResponseDetailShows
import com.example.moviescatalogue.data.remote.response.ResponseMovies
import com.example.moviescatalogue.data.remote.response.ResponseTvShows
import com.example.moviescatalogue.di.module.DispatcherModule.IoDispatcher
import com.example.moviescatalogue.ui.movies.MoviePagingSource
import com.example.moviescatalogue.ui.tvshows.TvShowsPagingSource
import com.example.moviescatalogue.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val network: ApiServices
) : MainDataSource {

    companion object{
        private const val NETWORK_PAGE_SIZE = 10
    }

    override suspend fun getMoviesApi(): Flow<PagingData<MoviesEntity>> {
        wrapEspressoIdlingResource {
            return Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { MoviePagingSource(network) }
            ).flow
        }
    }

    override suspend fun getShowsApi(): Flow<PagingData<TvShowsEntity>> {
        wrapEspressoIdlingResource {
            return Pager(
                config = PagingConfig(
                    pageSize = NETWORK_PAGE_SIZE,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { TvShowsPagingSource(network) }
            ).flow
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
}