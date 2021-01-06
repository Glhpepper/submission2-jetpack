package com.example.moviescatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviescatalogue.MainCoroutineRule
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.data.remote.response.*
import com.example.moviescatalogue.getOrAwaitValue
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class DetailViewModelTest {
    private lateinit var detailViewModel: DetailViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mainRepository = mock(MainRepository::class.java)

    @Before
    fun setupViewModel() {
        detailViewModel = DetailViewModel(mainRepository)
    }

    @Test
    fun getDetailMoviesApi_return_success() {
        val id = "0001"
        val genreList = ArrayList<GenresItemMovies>()
        genreList.add(GenresItemMovies("NAME"))
        val moviesList = ResponseDetailMovies(
            0,
            "TITLE",
            genreList,
            0.0,
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
        val movies = MutableLiveData(moviesList)

        mainCoroutineRule.runBlockingTest {
            whenever(mainRepository.getDetailMovie(id)).thenReturn(movies)
            detailViewModel.getDetailMovie(id)
            verify(mainRepository).getDetailMovie(id)
            detailViewModel.detailContentMovie.getOrAwaitValue()
            val detailEntity = detailViewModel.detailContentMovie.value

            assertThat(detailEntity, `is`(notNullValue()))
            assertThat(detailEntity?.genres?.size, `is`(1))
            assertThat(detailEntity, `is`(movies.value))
        }
    }

    @Test
    fun getDetailMoviesApi_return_successWithEmpty() {
        val id = "0001"
        val genreList = emptyList<GenresItemMovies>()
        val moviesList = ResponseDetailMovies(
            null,
            null,
            genreList,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
        val movies = MutableLiveData(moviesList)

        mainCoroutineRule.runBlockingTest {
            whenever(mainRepository.getDetailMovie(id)).thenReturn(movies)
            detailViewModel.getDetailMovie(id)
            verify(mainRepository).getDetailMovie(id)
            detailViewModel.detailContentMovie.getOrAwaitValue()
            val detailEntity = detailViewModel.detailContentMovie.value

            assertThat(detailEntity, `is`(notNullValue()))
            assertThat(detailEntity?.genres?.size, `is`(0))
        }
    }

    @Test
    fun getDetailShowsApi_return_success() {
        val id = "1000"
        val genreList = ArrayList<GenresItemShows>()
        genreList.add(GenresItemShows("NAME", 0))
        val seasonList = ArrayList<SeasonsItem>()
        seasonList.add(SeasonsItem("NAME", 0, "POSTERPATH"))
        val showsList = ResponseDetailShows(
            0,
            genreList,
            0.0,
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
        val shows = MutableLiveData(showsList)

        mainCoroutineRule.runBlockingTest {
            whenever(mainRepository.getDetailTvShows(id)).thenReturn(shows)
            detailViewModel.getDetailTvShows(id)
            verify(mainRepository).getDetailTvShows(id)
            detailViewModel.detailContentShows.getOrAwaitValue()
            val detailEntity = detailViewModel.detailContentShows.value

            assertThat(detailEntity, `is`(notNullValue()))
            assertThat(detailEntity?.genres?.size, `is`(1))
            assertThat(detailEntity, `is`(shows.value))
        }
    }

    @Test
    fun getDetailShowsApi_return_successWithEmpty() {
        val id = "1000"
        val genreList = emptyList<GenresItemShows>()
        val seasonList = emptyList<SeasonsItem>()
        val showsList = ResponseDetailShows(
            null,
            genreList,
            null,
            null,
            null,
            null,
            seasonList,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
        val shows = MutableLiveData(showsList)

        mainCoroutineRule.runBlockingTest {
            whenever(mainRepository.getDetailTvShows(id)).thenReturn(shows)
            detailViewModel.getDetailTvShows(id)
            verify(mainRepository).getDetailTvShows(id)
            detailViewModel.detailContentShows.getOrAwaitValue()
            val detailEntity = detailViewModel.detailContentShows.value

            assertThat(detailEntity, `is`(notNullValue()))
            assertThat(detailEntity?.genres?.size, `is`(0))
            assertThat(detailEntity?.seasons?.size, `is`(0))
        }
    }
}