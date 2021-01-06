package com.example.moviescatalogue.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviescatalogue.MainCoroutineRule
import com.example.moviescatalogue.data.local.entity.FavoriteShows
import com.example.moviescatalogue.data.local.room.CatalogueDatabase
import com.example.moviescatalogue.data.local.room.FavoriteShowsDao
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
    private lateinit var showsDao: FavoriteShowsDao
    private val showsList = ArrayList<FavoriteShows>()
    private val genreList = ArrayList<GenresItemShows>()
    private val seasonList = ArrayList<SeasonsItem>()

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

        showsDao = database.favoriteShowsDao()

        genreList.add(GenresItemShows("NAME", 0))
        seasonList.add(SeasonsItem("NAME", 0, "POSTERPATH"))
        showsList.add(
            FavoriteShows(
                0, genreList, 0.0, 0, "FIRSTAIRDATE", "OVERVIEW",
                seasonList, "POSTERPATH", "ORIGINALNAME",
                "VOTEAVERAGE", "NAME", "TAGLINE", "HOMEPAGE",
                "STATUS",
            )
        )
    }

    @After
    fun closeDb() = database.close()

    @Test
    fun checkFavoriteShows() = mainCoroutineRule.runBlockingTest {
        showsDao.insertFavoriteShows(showsList[0])
        val showsLoaded = showsList[0].id?.let { showsDao.checkFavorite(it) }

        assertThat(showsLoaded, notNullValue())
        assertThat(showsLoaded, `is`(true))
    }

    @Test
    fun deleteFavoriteShowsById() = mainCoroutineRule.runBlockingTest {
        showsDao.insertFavoriteShows(showsList[0])
        showsList[0].id?.let { showsDao.deleteFavoriteById(it) }

        val factory = showsDao.getFavoriteMShowPaging()
        val listShows = (factory.create() as LimitOffsetDataSource).loadRange(0, 4)

        assertThat(listShows.isEmpty(), `is`(true))
    }

    @Test
    fun insertFavoriteShows() = mainCoroutineRule.runBlockingTest {
        showsDao.insertFavoriteShows(showsList[0])

        val factory = showsDao.getFavoriteMShowPaging()
        val listShows = (factory.create() as LimitOffsetDataSource).loadRange(0, 4)

        assertThat(listShows, notNullValue())
        assertThat(listShows[0].id, `is`(0))
    }

    @Test
    fun deleteFavoriteShows() = mainCoroutineRule.runBlockingTest {
        showsDao.insertFavoriteShows(showsList[0])
        showsDao.deleteFavoriteUser(showsList[0])

        val factory = showsDao.getFavoriteMShowPaging()
        val listShows = (factory.create() as LimitOffsetDataSource).loadRange(0, 4)

        assertThat(listShows.isEmpty(), `is`(true))
    }
}