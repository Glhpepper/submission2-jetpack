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
            var moviesList: ArrayList<MoviesEntity>
            withContext(ioDispatcher) {
                moviesList = dummyData.generateDummyMovies()
            }
            moviesResults.value = moviesList
            return moviesResults
        }
    }

    override suspend fun getTvShows(): LiveData<List<TvShowsEntity>> {
        wrapEspressoIdlingResource {
            val tvShowsResults = MutableLiveData<List<TvShowsEntity>>()
            var showsList:ArrayList<TvShowsEntity>
            withContext(ioDispatcher) {
                showsList = dummyData.generateDummyShows()
            }
            tvShowsResults.value = showsList
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

    override suspend fun getDetailOffline(id: String): LiveData<DetailEntity> {
        wrapEspressoIdlingResource {
            val detailResults = MutableLiveData<DetailEntity>()
            var detail: ArrayList<DetailEntity>
            withContext(ioDispatcher) {
               detail = dummyData.generateDetail(id)
            }
            detailResults.value = detail[0]
            return detailResults
        }
    }
}