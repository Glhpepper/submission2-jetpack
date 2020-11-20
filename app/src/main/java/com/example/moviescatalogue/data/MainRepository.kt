package com.example.moviescatalogue.data

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
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val network: ApiServices,
    private val dummyData: DummyData
) : MainDataSource {

    override suspend fun getMovies(): ArrayList<MoviesEntity> {
        return withContext(ioDispatcher) {
            dummyData.generateDummyMovies()
        }
    }

    override suspend fun getTvShows(): ArrayList<TvShowsEntity> {
        return withContext(ioDispatcher) {
            dummyData.generateDummyShows()
        }
    }

    override suspend fun getMoviesApi(): ResponseMovies {
        return withContext(ioDispatcher) {
            network.getListMovies()
        }
    }

    override suspend fun getShowsApi(): ResponseTvShows {
        return withContext(ioDispatcher) {
            network.getListTvShows()
        }
    }

    override suspend fun getDetailMovie(id: String): ResponseDetailMovies {
        return withContext(ioDispatcher) {
            network.getDetailMovies(id)
        }
    }

    override suspend fun getDetailTvShows(id: String): ResponseDetailShows {
        return withContext(ioDispatcher) {
            network.getDetailTvShows(id)
        }
    }

    override suspend fun getDetailOffline(id: String): ArrayList<DetailEntity> {
        return withContext(ioDispatcher) {
            dummyData.generateDetail(id)
        }
    }
}