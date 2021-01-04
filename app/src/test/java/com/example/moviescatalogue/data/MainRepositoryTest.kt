package com.example.moviescatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.paging.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviescatalogue.MainCoroutineRule
import com.example.moviescatalogue.collectData
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.example.moviescatalogue.data.local.room.CatalogueDatabase
import com.example.moviescatalogue.data.remote.ApiServices
import com.example.moviescatalogue.data.remote.response.*
import com.example.moviescatalogue.getFlowAsLiveDataValue
import com.example.moviescatalogue.getOrAwaitValue
import com.example.moviescatalogue.ui.main.movies.MoviePagingSource
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
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MainRepositoryTest {

    private lateinit var mainRepository: MainRepository
    private val apiServices = mock(ApiServices::class.java)
    private val catalogueDatabase = mock(CatalogueDatabase::class.java)
    private val pager = mock(Pager::class.java)
    private val moviePagingSource = mock(PagingSource::class.java) as PagingSource<Int, MoviesEntity>
    private val networkPage = 20

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createRepository() {
        val moviesDao = catalogueDatabase.favoriteMovieDao()
        val showsDao = catalogueDatabase.favoriteShowsDao()
        mainRepository = MainRepository(Dispatchers.Main, apiServices, catalogueDatabase)
    }

    @Test
    fun getMoviesApi() {
        val moviesList = ArrayList<MoviesEntity>()
        moviesList.add(
            MoviesEntity(
                "1", "TITTLE", "OVERVIEW", "SCORE", "IMAGE", "DATE"
            )
        )
        val movies = MutableLiveData(PagingData.from(moviesList))
        val moviesResponse = ResponseMovies(moviesList)
        val moviesPager = Pager(
            config = PagingConfig(
                pageSize = networkPage,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(apiServices) }
        ).liveData

        mainCoroutineRule.runBlockingTest {
            whenever(apiServices.getListMovies(page = 1)).thenReturn(moviesResponse)
            whenever(pager.liveData).thenReturn(moviesPager)
            mainRepository.getMoviesApi()
            verify(pager.liveData)
            verify(apiServices).getListMovies(page = 1)
            val moviesEntity = mainRepository.getMoviesApi().getFlowAsLiveDataValue()
            val title = moviesEntity?.map { it.moviesTitle }

            assertThat(moviesEntity, `is`(notNullValue()))
            assertThat(title?.collectData(), `is`(listOf("TITTLE")))
        }
    }

//    @Test
//    fun getShowsApi() {
//        val showsList = ArrayList<TvShowsEntity>()
//        showsList.add(
//            TvShowsEntity(
//                "1", "TITTLE", "OVERVIEW", "SCORE", "IMAGE", "DATE"
//            )
//        )
//        val shows = ResponseTvShows(showsList)
//
//        mainCoroutineRule.runBlockingTest {
//            whenever(apiServices.getListTvShows()).thenReturn(shows)
//            mainRepository.getShowsApi()
//            verify(apiServices).getListTvShows()
//
//            val showsEntity = mainRepository.getShowsApi().getOrAwaitValue()
//            assertThat(showsEntity, `is`(notNullValue()))
//            assertThat(showsEntity.results?.size, `is`(1))
//            assertThat(showsEntity, `is`(shows))
//        }
//    }
//
//    @Test
//    fun getDetailMovies() {
//        val id = "0001"
//        val genreList = ArrayList<GenresItemMovies>()
//        genreList.add(GenresItemMovies("NAME"))
//        val movies = ResponseDetailMovies(
//            "TITTLE",
//            genreList,
//            0.0,
//            0,
//            0,
//            "OVERVIEW",
//            "ORIGINALTITLE",
//            "POSTERPATH",
//            "BACKDROPPATH",
//            "RELEASEDATE",
//            0.0,
//            "TAGLINE",
//            "HOMEPAGE",
//            "STATUS"
//        )
//
//        mainCoroutineRule.runBlockingTest {
//            whenever(apiServices.getDetailMovies(id)).thenReturn(movies)
//            mainRepository.getDetailMovie(id)
//            verify(apiServices).getDetailMovies(id)
//
//            val detailEntity = mainRepository.getDetailMovie(id).getOrAwaitValue()
//            assertThat(detailEntity, `is`(notNullValue()))
//            assertThat(detailEntity.genres?.size, `is`(1))
//            assertThat(detailEntity, `is`(movies))
//        }
//    }
//
//    @Test
//    fun getDetailShows() {
//        val id = "1000"
//        val genreList = ArrayList<GenresItemShows>()
//        genreList.add(GenresItemShows("NAME", 0))
//        val seasonList = ArrayList<SeasonsItem>()
//        seasonList.add(SeasonsItem("NAME", 0, "POSTERPATH"))
//        val shows = ResponseDetailShows(
//            genreList,
//            0.0,
//            0,
//            0,
//            "FIRSTAIRDATE",
//            "OVERVIEW",
//            seasonList,
//            "POSTERPATH",
//            "BACKDROPPATH",
//            0.0,
//            "NAME",
//            "TAGLINE",
//            "HOMEPAGE",
//            "STATUS"
//        )
//
//        mainCoroutineRule.runBlockingTest {
//            whenever(apiServices.getDetailTvShows(id)).thenReturn(shows)
//            mainRepository.getDetailTvShows(id)
//            verify(apiServices).getDetailTvShows(id)
//
//            val detailEntity = mainRepository.getDetailTvShows(id).getOrAwaitValue()
//            assertThat(detailEntity, Matchers.`is`(Matchers.notNullValue()))
//            assertThat(detailEntity.genres?.size, Matchers.`is`(1))
//            assertThat(detailEntity.seasons?.size, Matchers.`is`(1))
//            assertThat(detailEntity, `is`(shows))
//        }
//    }
}