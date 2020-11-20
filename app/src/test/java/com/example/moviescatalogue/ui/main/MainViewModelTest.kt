package com.example.moviescatalogue.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviescatalogue.MainCoroutineRule
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.utils.DummyData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mainRepository: MainRepository

    @Mock
    private lateinit var observer: Observer<List<MoviesEntity>?>

    @Before
    fun setupViewModel() {
        mainViewModel = MainViewModel(mainRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = DummyData.generateDummyMovies()
        val movies = ArrayList<MoviesEntity>()
        movies.addAll(dummyMovies)

        mainCoroutineRule.runBlockingTest {
            `when`(mainRepository.getMovies()).thenReturn(movies)
            val moviesEntity = mainViewModel.listMoviesApi.value
            verify(mainRepository).getMovies()
            assertThat(moviesEntity, `is`(notNullValue()))
            assertThat(moviesEntity?.size, `is`(5))

            mainViewModel.listMoviesApi.observeForever(observer)
            verify(observer).onChanged(movies)
        }

    }

}