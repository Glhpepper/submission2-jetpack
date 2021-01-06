package com.example.moviescatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviescatalogue.MainCoroutineRule
import com.example.moviescatalogue.PagedListUtil
import com.example.moviescatalogue.data.local.LocalDataSource
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.entity.FavoriteShows
import com.example.moviescatalogue.data.local.entity.MoviesEntity
import com.example.moviescatalogue.data.local.entity.TvShowsEntity
import com.example.moviescatalogue.data.remote.RemoteDataSource
import com.example.moviescatalogue.data.remote.response.*
import com.example.moviescatalogue.getOrAwaitValue
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MainRepositoryTest {

    private lateinit var mainRepository: MainRepository
    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createRepository() {
        mainRepository = MainRepository(Dispatchers.Main, remoteDataSource, localDataSource)
    }

    @Test
    fun getMoviesApi() {
        val moviesList = ArrayList<MoviesEntity>()
        moviesList.add(
            MoviesEntity(
                "1", "TITTLE", "OVERVIEW", "SCORE", "IMAGE", "DATE"
            )
        )
        val moviesResponse = ResponseMovies(moviesList)

        mainCoroutineRule.runBlockingTest {
            whenever(remoteDataSource.getListMovies()).thenReturn(moviesResponse)
            mainRepository.getMoviesApi()
            verify(remoteDataSource).getListMovies()
            val moviesEntity = mainRepository.getMoviesApi().getOrAwaitValue()

            assertThat(moviesEntity, `is`(notNullValue()))
            assertThat(moviesEntity.results.size, `is`(1))
            assertThat(moviesEntity, `is`(moviesResponse))
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
            whenever(remoteDataSource.getListShows()).thenReturn(shows)
            mainRepository.getShowsApi()
            verify(remoteDataSource).getListShows()
            val showsEntity = mainRepository.getShowsApi().getOrAwaitValue()

            assertThat(showsEntity, `is`(notNullValue()))
            assertThat(showsEntity.results.size, `is`(1))
            assertThat(showsEntity, `is`(shows))
        }
    }

    @Test
    fun getDetailMovies() {
        val id = "0001"
        val genreList = ArrayList<GenresItemMovies>()
        genreList.add(GenresItemMovies("NAME"))
        val movies = ResponseDetailMovies(
            0,
            "TITTLE",
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

        mainCoroutineRule.runBlockingTest {
            whenever(remoteDataSource.getDetailMovies(id)).thenReturn(movies)
            mainRepository.getDetailMovie(id)
            verify(remoteDataSource).getDetailMovies(id)
            val detailEntity = mainRepository.getDetailMovie(id).getOrAwaitValue()

            assertThat(detailEntity, `is`(notNullValue()))
            assertThat(detailEntity.genres?.size, `is`(1))
            assertThat(detailEntity, `is`(movies))
        }
    }

    @Test
    fun getDetailShows() {
        val id = "1000"
        val genreList = ArrayList<GenresItemShows>()
        genreList.add(GenresItemShows("NAME", 0))
        val seasonList = ArrayList<SeasonsItem>()
        seasonList.add(SeasonsItem("NAME", 0, "POSTERPATH"))
        val shows = ResponseDetailShows(
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

        mainCoroutineRule.runBlockingTest {
            whenever(remoteDataSource.getDetailShows(id)).thenReturn(shows)
            mainRepository.getDetailTvShows(id)
            verify(remoteDataSource).getDetailShows(id)
            val detailEntity = mainRepository.getDetailTvShows(id).getOrAwaitValue()

            assertThat(detailEntity, `is`(notNullValue()))
            assertThat(detailEntity.genres?.size, `is`(1))
            assertThat(detailEntity.seasons?.size, `is`(1))
            assertThat(detailEntity, `is`(shows))
        }
    }

    @Test
    fun getMoviesFavoritePaging() {
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

        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteMovies>

        mainCoroutineRule.runBlockingTest {
            whenever(localDataSource.getAllFavoriteMovies()).thenReturn(dataSourceFactory)
            mainRepository.getMoviesFavoritePaging()
            verify(localDataSource).getAllFavoriteMovies()
            val moviesFavorite = PagedListUtil.mockPagedList(moviesList)

            assertThat(moviesFavorite, `is`(notNullValue()))
            assertThat(moviesFavorite[0]?.id, `is`(0))
        }
    }

    @Test
    fun getShowsFavoritePaging() {
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
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteShows>

        mainCoroutineRule.runBlockingTest {
            whenever(localDataSource.getAllFavoriteShows()).thenReturn(dataSourceFactory)
            mainRepository.getShowsFavoritePaging()
            verify(localDataSource).getAllFavoriteShows()
            val courseEntities = PagedListUtil.mockPagedList(showsList)

            assertThat(courseEntities, `is`(notNullValue()))
            assertThat(courseEntities[0]?.id, `is`(0))
        }
    }
}