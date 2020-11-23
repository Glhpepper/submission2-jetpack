package com.example.moviescatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviescatalogue.MainCoroutineRule
import com.example.moviescatalogue.data.local.entity.DetailEntity
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.example.moviescatalogue.data.remote.ApiServices
import com.example.moviescatalogue.data.remote.RetrofitClient.getNetworkService
import com.example.moviescatalogue.data.remote.response.*
import com.example.moviescatalogue.getOrAwaitValue
import com.example.moviescatalogue.utils.DummyData
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.hamcrest.Matchers.empty
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MainRepositoryTest {

    private lateinit var mainRepository: MainRepository
    private lateinit var dummyData: DummyData
    private val apiServices = mock(ApiServices::class.java)

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createRepository() {
        dummyData = DummyData

        mainRepository = MainRepository(Dispatchers.Main, apiServices, dummyData)
    }

    @Test
    fun getMovies() {
        val movies = dummyData.generateDummyMovies()

        mainCoroutineRule.runBlockingTest {
            mainRepository.getMovies()
            val moviesEntity = mainRepository.getMovies().getOrAwaitValue()

            assertThat(moviesEntity, `is`(allOf(notNullValue(), not(empty()))))
            assertThat(moviesEntity.size, `is`(movies.size))
        }
    }

    @Test
    fun getTVShows() {
        val shows = dummyData.generateDummyShows()

        mainCoroutineRule.runBlockingTest {
            mainRepository.getTvShows()
            val showsEntity = mainRepository.getTvShows().getOrAwaitValue()

            assertThat(showsEntity, `is`(allOf(notNullValue(), not(empty()))))
            assertThat(showsEntity.size, `is`(shows.size))
        }
    }

    @Test
    fun getDetailOffline() {
        val dummyMovies = dummyData.generateDummyMovies()
        val moviesId = dummyMovies[0].moviesId
        val moviesDetail = dummyData.generateDetail(moviesId)

        val dummyShows = dummyData.generateDummyShows()
        val showsId = dummyShows[0].showsId
        val showsDetail = dummyData.generateDetail(showsId)

        mainCoroutineRule.runBlockingTest {
            mainRepository.getDetailOffline(moviesId)
            val moviesEntity = mainRepository.getDetailOffline(moviesId).getOrAwaitValue()

            assertThat(moviesDetail, `is`(allOf(notNullValue(), not(empty()))))
            assertThat(moviesDetail.size, `is`(moviesEntity.size))

            mainRepository.getDetailOffline(showsId)
            val showsEntity = mainRepository.getDetailOffline(showsId).getOrAwaitValue()

            assertThat(showsDetail, `is`(allOf(notNullValue(), not(empty()))))
            assertThat(showsDetail.size, `is`(showsEntity.size))
        }
    }

    @Test
    fun getMoviesApi() {
        val moviesList = ArrayList<MoviesEntity>()
        moviesList.add(
            MoviesEntity(
                "1", "TITTLE", "OVERVIEW", "SCORE", "IMAGE", "DATE"
            )
        )
        val movies = ResponseMovies(moviesList)

        mainCoroutineRule.runBlockingTest {
            whenever(apiServices.getListMovies()).thenReturn(movies)
            mainRepository.getMoviesApi()
            verify(apiServices).getListMovies()

            val moviesEntity = mainRepository.getMoviesApi().getOrAwaitValue()
            assertThat(moviesEntity, `is`(notNullValue()))
            assertThat(moviesEntity.results?.size, `is`(1))
        }
    }

    @Test
    fun getShowsApi() {
        val showsList = ArrayList<TvShowsEntity>()
        showsList.add(
            TvShowsEntity(
                "1", "TITTLE", "OVERVIEW", "SCORE", "IMAGE", "DATE"
            )
        )
        val shows = ResponseTvShows(showsList)

        mainCoroutineRule.runBlockingTest {
            whenever(apiServices.getListTvShows()).thenReturn(shows)
            mainRepository.getShowsApi()
            verify(apiServices).getListTvShows()

            val showsEntity = mainRepository.getShowsApi().getOrAwaitValue()
            assertThat(showsEntity, `is`(notNullValue()))
            assertThat(showsEntity.results?.size, `is`(1))
        }
    }

    @Test
    fun getDetailMovies() {
        val id = "0001"
        val genreList = ArrayList<GenresItemMovies>()
        genreList.add(GenresItemMovies("NAME"))
        val movies = ResponseDetailMovies(
            "TITTLE",
            genreList,
            0.0,
            0,
            0,
            "OVERVIEW",
            "ORIGINALTITLE",
            "POSTERPATH",
            "BACKDROPPATH",
            "RELEASEDATE",
            0.0,
            "TAGLINE",
            "HOMEPAGE",
            "STATUS"
        )

        mainCoroutineRule.runBlockingTest {
            whenever(apiServices.getDetailMovies(id)).thenReturn(movies)
            mainRepository.getDetailMovie(id)
            verify(apiServices).getDetailMovies(id)

            val detailEntity = mainRepository.getDetailMovie(id).getOrAwaitValue()
            assertThat(detailEntity, `is`(notNullValue()))
            assertThat(detailEntity.genres?.size, `is`(1))
        }
    }

    @Test
    fun getDetailShows() {
        val id = "1000"
        val genreList = ArrayList<GenresItemShows>()
        genreList.add(GenresItemShows("NAME", 0))
        val seasonList = ArrayList<SeasonsItem>()
        seasonList.add(SeasonsItem("NAME", 0,"POSTERPATH"))
        val shows = ResponseDetailShows(
            genreList,
            0.0,
            0,
            0,
            "FIRSTAIRDATE",
            "OVERVIEW",
            seasonList,
            "POSTERPATH",
            "BACKDROPPATH",
            0.0,
            "NAME",
            "TAGLINE",
            "HOMEPAGE",
            "STATUS"
        )

        mainCoroutineRule.runBlockingTest {
            whenever(apiServices.getDetailTvShows(id)).thenReturn(shows)
            mainRepository.getDetailTvShows(id)
            verify(apiServices).getDetailTvShows(id)

            val detailEntity = mainRepository.getDetailTvShows(id).getOrAwaitValue()
            assertThat(detailEntity, Matchers.`is`(Matchers.notNullValue()))
            assertThat(detailEntity.genres?.size, Matchers.`is`(1))
            assertThat(detailEntity.seasons?.size, Matchers.`is`(1))
        }
    }
}