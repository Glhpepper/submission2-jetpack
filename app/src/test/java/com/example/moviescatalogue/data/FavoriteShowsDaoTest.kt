package com.example.moviescatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviescatalogue.MainCoroutineRule
import com.example.moviescatalogue.data.local.entity.FavoriteShows
import com.example.moviescatalogue.data.local.room.CatalogueDatabase
import com.example.moviescatalogue.data.remote.response.GenresItemShows
import com.example.moviescatalogue.data.remote.response.SeasonsItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class FavoriteShowsDaoTest {
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
    fun checkFavoriteShows() = mainCoroutineRule.runBlockingTest {
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
        database.favoriteShowsDao().insertFavoriteShows(showsList[0])
        val movieLoaded = showsList[0].id?.let { database.favoriteShowsDao().checkFavorite(it) }

        assertThat(movieLoaded, notNullValue())
        assertThat(movieLoaded, `is`(true))
    }

    @Test
    fun deleteFavoriteShowsById() = mainCoroutineRule.runBlockingTest {
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
        database.favoriteShowsDao().insertFavoriteShows(showsList[0])
        showsList[0].id?.let { database.favoriteShowsDao().deleteFavoriteById(it) }

        val factory = database.favoriteShowsDao().getFavoriteMShowPaging()
        val listMovies = (factory.create() as LimitOffsetDataSource).loadRange(0, 4)

        assertThat(listMovies.isEmpty(), `is`(true))
    }

    @Test
    fun insertFavoriteShows() = mainCoroutineRule.runBlockingTest {
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
        database.favoriteShowsDao().insertFavoriteShows(showsList[0])

        val factory = database.favoriteShowsDao().getFavoriteMShowPaging()
        val listMovies = (factory.create() as LimitOffsetDataSource).loadRange(0, 4)

        assertThat(listMovies, notNullValue())
        assertThat(listMovies[0].name, `is`("NAME"))
    }

    @Test
    fun deleteFavoriteShows() = mainCoroutineRule.runBlockingTest {
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
        database.favoriteShowsDao().insertFavoriteShows(showsList[0])
        database.favoriteShowsDao().deleteFavoriteUser(showsList[0])

        val factory = database.favoriteShowsDao().getFavoriteMShowPaging()
        val listMovies = (factory.create() as LimitOffsetDataSource).loadRange(0, 4)

        assertThat(listMovies.isEmpty(), `is`(true))
    }
}