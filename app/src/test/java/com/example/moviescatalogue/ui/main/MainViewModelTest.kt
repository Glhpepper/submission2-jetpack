package com.example.moviescatalogue.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.paging.PagedList
import androidx.paging.PagingData
import androidx.paging.map
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviescatalogue.MainCoroutineRule
import com.example.moviescatalogue.collectData
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.example.moviescatalogue.data.remote.response.ResponseMovies
import com.example.moviescatalogue.data.remote.response.ResponseTvShows
import com.example.moviescatalogue.getFlowAsLiveDataValue
import com.example.moviescatalogue.getOrAwaitValue
import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*

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
    fun getMoviesApiPaging_return_success() {
        val moviesList = ArrayList<MoviesEntity>()
        moviesList.add(MoviesEntity(
            "1", "TITTLE", "OVERVIEW", "SCORE", "IMAGE", "DATE"
        ))

        val movies = MutableLiveData(PagingData.from(moviesList))
        mainCoroutineRule.runBlockingTest {
            whenever(mainRepository.getMoviesApi()).thenReturn(movies)
            mainViewModel.getMovieApiPaging()
            verify(mainRepository).getMoviesApi()
            mainViewModel.listMoviesApi.getFlowAsLiveDataValue()
            val moviesEntity = mainViewModel.listMoviesApi.value
            val title = moviesEntity?.map { it.moviesTitle }

            assertThat(moviesEntity, `is`(notNullValue()))
            assertThat(title?.collectData(), `is`(listOf("TITTLE")))
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
        val shows = MutableLiveData(PagingData.from(showsList))

        mainCoroutineRule.runBlockingTest {
            whenever(mainRepository.getShowsApi()).thenReturn(shows)
            mainViewModel.getShowsApiPaging()
            verify(mainRepository).getShowsApi()
            mainViewModel.listShowsApi.getFlowAsLiveDataValue()
            val showsEntity = mainViewModel.listShowsApi.value
            val title = showsEntity?.map { it.showsTitle }

            assertThat(showsEntity, `is`(notNullValue()))
            assertThat(title?.collectData(), `is`(listOf("TITTLE")))
        }
    }
}