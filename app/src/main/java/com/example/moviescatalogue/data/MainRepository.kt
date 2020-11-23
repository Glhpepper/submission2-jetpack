package com.example.moviescatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviescatalogue.data.local.entity.DetailEntity
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.example.moviescatalogue.data.remote.ApiServices
import com.example.moviescatalogue.data.remote.response.ResponseDetailMovies
import com.example.moviescatalogue.data.remote.response.ResponseDetailShows
import com.example.moviescatalogue.data.remote.response.ResponseMovies
import com.example.moviescatalogue.data.remote.response.ResponseTvShows
import com.example.moviescatalogue.di.module.DispatcherModule.IoDispatcher
import com.example.moviescatalogue.utils.DummyData
import com.example.moviescatalogue.utils.wrapEspressoIdlingResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val network: ApiServices,
    private val dummyData: DummyData
) : MainDataSource {

    override suspend fun getMovies(): LiveData<List<MoviesEntity>> {
        wrapEspressoIdlingResource {
            val moviesResults = MutableLiveData<List<MoviesEntity>>()
            withContext(ioDispatcher) {
                val moviesList = dummyData.generateDummyMovies()
                moviesResults.postValue(moviesList)
            }
            return moviesResults
        }
    }

    override suspend fun getTvShows(): LiveData<List<TvShowsEntity>> {
        wrapEspressoIdlingResource {
            val tvShowsResults = MutableLiveData<List<TvShowsEntity>>()
            withContext(ioDispatcher) {
                val showsList = dummyData.generateDummyShows()
                tvShowsResults.postValue(showsList)
            }
            return tvShowsResults
        }
    }

    override suspend fun getMoviesApi(): LiveData<ResponseMovies> {
        wrapEspressoIdlingResource {
            val moviesResults = MutableLiveData<ResponseMovies>()
            withContext(ioDispatcher) {
                val moviesList = network.getListMovies()
                moviesResults.postValue(moviesList)
            }
            return moviesResults
        }
    }

    override suspend fun getShowsApi(): LiveData<ResponseTvShows> {
        wrapEspressoIdlingResource {
            val tvShowsResults = MutableLiveData<ResponseTvShows>()
            withContext(ioDispatcher) {
                val showsList = network.getListTvShows()
                tvShowsResults.postValue(showsList)
            }
            return tvShowsResults
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

    override suspend fun getDetailOffline(id: String): LiveData<List<DetailEntity>> {
        wrapEspressoIdlingResource {
            val detailResults = MutableLiveData<List<DetailEntity>>()
            withContext(ioDispatcher) {
                val detail = dummyData.generateDetail(id)
                detailResults.postValue(detail)
            }
            return detailResults
        }
    }
}