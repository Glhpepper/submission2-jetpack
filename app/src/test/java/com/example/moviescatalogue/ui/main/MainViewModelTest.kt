package com.example.moviescatalogue.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviescatalogue.MainCoroutineRule
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.example.moviescatalogue.data.remote.response.ResponseMovies
import com.example.moviescatalogue.data.remote.response.ResponseTvShows
import com.example.moviescatalogue.getOrAwaitValue
import com.example.moviescatalogue.utils.DummyData
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MainViewModelTest {
    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mainRepository = mock(MainRepository::class.java)

    @Before
    fun setupViewModel() {
        mainViewModel = MainViewModel(mainRepository)
    }

    @Test
    fun getMovies_return_movieOffline() {
        val dummyMovies = DummyData.generateDummyMovies()
        val movies = MutableLiveData<List<MoviesEntity>>(dummyMovies)

        mainCoroutineRule.runBlockingTest {
            whenever(mainRepository.getMovies()).thenReturn(movies)
            mainViewModel.getMovies()
            verify(mainRepository).getMovies()
            mainViewModel.listMoviesApi.getOrAwaitValue()
            val moviesEntity = mainViewModel.listMoviesApi.value
            assertThat(moviesEntity, `is`(allOf(notNullValue(), not(empty()))))
            assertThat(moviesEntity?.size, `is`(5))
        }
    }

    @Test
    fun getMoviesOfEmpty_return_movieOfflineWithEmptyList() {
        val movies = MutableLiveData<List<MoviesEntity>>(emptyList())

        mainCoroutineRule.runBlockingTest {
            whenever(mainRepository.getMovies()).thenReturn(movies)
            mainViewModel.getMovies()
            verify(mainRepository).getMovies()
            mainViewModel.listMoviesApi.getOrAwaitValue()
            val moviesEntity = mainViewModel.listMoviesApi.value
            assertThat(moviesEntity, `is`(allOf(notNullValue(), empty())))
            assertThat(moviesEntity?.size, `is`(0))
        }
    }

    @Test
    fun getMoviesApi_return_success() {
        val moviesList = ArrayList<MoviesEntity>()
        moviesList.add(
            MoviesEntity(
                "1", "TITTLE", "OVERVIEW", "SCORE", "IMAGE", "DATE"
            )
        )
        val responseMovie = ResponseMovies(moviesList)
        val movies = MutableLiveData(responseMovie)

        mainCoroutineRule.runBlockingTest {
            whenever(mainRepository.getMoviesApi()).thenReturn(movies)
            mainViewModel.getMoviesApi()
            verify(mainRepository).getMoviesApi()
            mainViewModel.listMoviesApi.getOrAwaitValue()
            val moviesEntity = mainViewModel.listMoviesApi.value
            assertThat(moviesEntity, `is`(allOf(notNullValue(), not(empty()))))
            assertThat(moviesEntity?.size, `is`(1))
        }
    }

    @Test
    fun getMoviesApi_return_successWithEmptyList() {
        val moviesList = ResponseMovies(emptyList())
        val movies = MutableLiveData(moviesList)

        mainCoroutineRule.runBlockingTest {
            whenever(mainRepository.getMoviesApi()).thenReturn(movies)
            mainViewModel.getMoviesApi()
            verify(mainRepository).getMoviesApi()
            mainViewModel.listMoviesApi.getOrAwaitValue()
            val moviesEntity = mainViewModel.listMoviesApi.value
            assertThat(moviesEntity, `is`(allOf(notNullValue(), empty())))
            assertThat(moviesEntity?.size, `is`(0))
        }
    }

    @Test
    fun getShows_return_showsOffline() {
        val dummyShows = DummyData.generateDummyShows()
        val shows = MutableLiveData<List<TvShowsEntity>>(dummyShows)

        mainCoroutineRule.runBlockingTest {
            whenever(mainRepository.getTvShows()).thenReturn(shows)
            mainViewModel.getTvShows()
            verify(mainRepository).getTvShows()
            mainViewModel.listShowsApi.getOrAwaitValue()
            val showsEntity = mainViewModel.listShowsApi.value
            assertThat(showsEntity, `is`(allOf(notNullValue(), not(empty()))))
            assertThat(showsEntity?.size, `is`(5))
        }
    }

    @Test
    fun getShowsOfEmpty_return_showsOfflineWithEmptyList() {
        val shows = MutableLiveData<List<TvShowsEntity>>(emptyList())

        mainCoroutineRule.runBlockingTest {
            whenever(mainRepository.getTvShows()).thenReturn(shows)
            mainViewModel.getTvShows()
            verify(mainRepository).getTvShows()
            mainViewModel.listShowsApi.getOrAwaitValue()
            val showsEntity = mainViewModel.listShowsApi.value
            assertThat(showsEntity, `is`(allOf(notNullValue(), empty())))
            assertThat(showsEntity?.size, `is`(0))
        }
    }

    @Test
    fun getShowsApi_return_success() {
        val showsList = ArrayList<TvShowsEntity>()
        showsList.add(
            TvShowsEntity(
                "1", "TITTLE", "OVERVIEW", "SCORE", "IMAGE", "DATE"
            )
        )
        val responseShows = ResponseTvShows(showsList)
        val shows = MutableLiveData(responseShows)

        mainCoroutineRule.runBlockingTest {
            whenever(mainRepository.getShowsApi()).thenReturn(shows)
            mainViewModel.getTvShowsApi()
            verify(mainRepository).getShowsApi()
            mainViewModel.listShowsApi.getOrAwaitValue()
            val showsEntity = mainViewModel.listShowsApi.value
            assertThat(showsEntity, `is`(allOf(notNullValue(), not(empty()))))
            assertThat(showsEntity?.size, `is`(1))
        }
    }

    @Test
    fun getShowsApi_return_successWithEmptyList() {
        val showsList = ResponseTvShows(emptyList())
        val shows = MutableLiveData(showsList)

        mainCoroutineRule.runBlockingTest {
            whenever(mainRepository.getShowsApi()).thenReturn(shows)
            mainViewModel.getTvShowsApi()
            verify(mainRepository).getShowsApi()
            mainViewModel.listShowsApi.getOrAwaitValue()
            val showsEntity = mainViewModel.listShowsApi.value
            assertThat(showsEntity, `is`(allOf(notNullValue(), empty())))
            assertThat(showsEntity?.size, `is`(0))
        }
    }


}