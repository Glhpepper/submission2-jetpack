package com.example.moviescatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviescatalogue.MainCoroutineRule
import com.example.moviescatalogue.data.local.entity.FavoriteMovies
import com.example.moviescatalogue.data.local.room.CatalogueDatabase
import com.example.moviescatalogue.data.remote.response.GenresItemMovies
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class FavoriteMoviesDaoTest {
    private lateinit var database: CatalogueDatabase

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CatalogueDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun checkFavoriteMovies() = mainCoroutineRule.runBlockingTest {
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
        database.favoriteMovieDao().insertFavoriteMovies(moviesList[0])
        val movieLoaded = moviesList[0].id?.let { database.favoriteMovieDao().checkFavorite(it) }

        assertThat(movieLoaded, notNullValue())
        assertThat(movieLoaded, `is`(true))
    }

    @Test
    fun deleteFavoriteMoviesById() = mainCoroutineRule.runBlockingTest {
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
        database.favoriteMovieDao().insertFavoriteMovies(moviesList[0])
        moviesList[0].id?.let { database.favoriteMovieDao().deleteFavoriteById(it) }

        val factory = database.favoriteMovieDao().getFavoriteMoviePaging()
        val listMovies = (factory.create() as LimitOffsetDataSource).loadRange(0, 4)

        assertThat(listMovies.isEmpty(), `is`(true))
    }

    @Test
    fun insertFavoriteMovies() = mainCoroutineRule.runBlockingTest {
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
        database.favoriteMovieDao().insertFavoriteMovies(moviesList[0])

        val factory = database.favoriteMovieDao().getFavoriteMoviePaging()
        val listMovies = (factory.create() as LimitOffsetDataSource).loadRange(0, 4)

        assertThat(listMovies, notNullValue())
        assertThat(listMovies[0].title, `is`("TITTLE"))
    }

    @Test
    fun deleteFavoriteMovies() = mainCoroutineRule.runBlockingTest {
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
        database.favoriteMovieDao().insertFavoriteMovies(moviesList[0])
        database.favoriteMovieDao().deleteFavoriteUser(moviesList[0])

        val factory = database.favoriteMovieDao().getFavoriteMoviePaging()
        val listMovies = (factory.create() as LimitOffsetDataSource).loadRange(0, 4)

        assertThat(listMovies.isEmpty(), `is`(true))
    }
}