package com.example.moviescatalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviescatalogue.MainCoroutineRule
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.remote.response.ResponseMovies
import com.example.moviescatalogue.getOrAwaitValue
import com.example.moviescatalogue.ui.main.MainViewModel
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class FavoriteViewModelTest {
    private lateinit var favoriteViewModel: FavoriteViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mainRepository = Mockito.mock(MainRepository::class.java)

    @Mock
    private lateinit var favoriteMoviePaging: PagingData<FavoriteMovies>

    @Before
    fun setupViewModel() {
        favoriteViewModel = FavoriteViewModel(mainRepository)
    }

    fun getFavoriteMoviePaging_return_success() {
        val moviesList = MutableLiveData(favoriteMoviePaging)
        `when`(moviesList.value.size).thenReturn(5)
        val moviesPagingData = PagingData<MoviesEntity>()
        moviesList.postValue()
        MoviesEntity(
            "1", "TITTLE", "OVERVIEW", "SCORE", "IMAGE", "DATE"
        )
        val responseMovie = ResponseMovies(moviesList)
        val movies = MutableLiveData(responseMovie)

        mainCoroutineRule.runBlockingTest {
            whenever(mainRepository.getMoviesApi()).thenReturn(movies)
            mainViewModel.getMoviesApi()
            Mockito.verify(mainRepository).getMoviesApi()
            mainViewModel.listMoviesApi.getOrAwaitValue()
            val moviesEntity = mainViewModel.listMoviesApi.value
            MatcherAssert.assertThat(
                moviesEntity,
                Matchers.`is`(
                    Matchers.allOf(
                        Matchers.notNullValue(),
                        Matchers.not(Matchers.empty())
                    )
                )
            )
            MatcherAssert.assertThat(moviesEntity?.size, Matchers.`is`(1))
            MatcherAssert.assertThat(
                moviesEntity?.get(0),
                Matchers.`is`(movies.value?.results?.get(0))
            )
        }
    }
}