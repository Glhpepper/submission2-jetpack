package com.example.moviescatalogue.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviescatalogue.MainCoroutineRule
import com.example.moviescatalogue.PagedListUtil
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.entity.FavoriteShows
import com.example.moviescatalogue.data.remote.response.GenresItemMovies
import com.example.moviescatalogue.data.remote.response.GenresItemShows
import com.example.moviescatalogue.data.remote.response.SeasonsItem
import com.example.moviescatalogue.getOrAwaitValue
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class FavoriteViewModelTest {
    private lateinit var favoriteViewModel: FavoriteViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val mainRepository = mock(MainRepository::class.java)

    @Before
    fun setupViewModel() {
        favoriteViewModel = FavoriteViewModel(mainRepository)
    }

    @Test
    fun getFavoriteMoviePaging_return_success() {
        val moviesList = ArrayList<FavoriteMovies>()
        val genreList = ArrayList<GenresItemMovies>()
        genreList.add(GenresItemMovies("NAME"))
        moviesList.add(
            FavoriteMovies(
                0, "TITTLE", genreList, 0.0, 0, "DATE",
                "ORIGINALLTITLE", "POSTERPATH", "BACKDROPPATH",
                "RELEASEDATE", "VOTEAVERAGE", "TAGLINE", "HOMEPAGE",
                "STATUS",
            )
        )
        val movies = MutableLiveData<PagedList<FavoriteMovies>>()
        movies.value = PagedListUtil.mockPagedList(moviesList)

        mainCoroutineRule.runBlockingTest {
            whenever(mainRepository.getMoviesFavoritePaging()).thenReturn(movies)
            favoriteViewModel.getFavoriteMoviePaging()
            verify(mainRepository).getMoviesFavoritePaging()
            favoriteViewModel.getFavoriteMoviePaging().getOrAwaitValue()
            val moviesEntity = favoriteViewModel.getFavoriteMoviePaging().value

            assertThat(moviesEntity, `is`(notNullValue()))
            assertThat(moviesEntity?.get(0)?.title, `is`("TITTLE"))
            assertThat(moviesEntity?.size, `is`(1))
        }
    }

    @Test
    fun getFavoriteShowsPaging_return_success() {
        val showsList = ArrayList<FavoriteShows>()
        val genreList = ArrayList<GenresItemShows>()
        genreList.add(GenresItemShows("NAME", 0))
        val seasonList = ArrayList<SeasonsItem>()
        seasonList.add(SeasonsItem("NAME", 0, "POSTERPATH"))
        showsList.add(
            FavoriteShows(
                0, genreList, 0.0, 0, "FIRSTAIRDATE", "OVERVIEW",
                seasonList, "POSTERPATH", "ORIGINALNAME",
                "VOTEAVERAGE", "NAME", "TAGLINE", "HOMEPAGE",
                "STATUS",
            )
        )
        val shows = MutableLiveData<PagedList<FavoriteShows>>()
        shows.value = PagedListUtil.mockPagedList(showsList)

        mainCoroutineRule.runBlockingTest {
            whenever(mainRepository.getShowsFavoritePaging()).thenReturn(shows)
            favoriteViewModel.getFavoriteShowsPaging()
            verify(mainRepository).getShowsFavoritePaging()
            favoriteViewModel.getFavoriteShowPaging().getOrAwaitValue()
            val showsEntity = favoriteViewModel.getFavoriteShowPaging().value

            assertThat(showsEntity, `is`(notNullValue()))
            assertThat(showsEntity?.get(0)?.name, `is`("NAME"))
            assertThat(showsEntity?.size, `is`(1))
        }
    }
}