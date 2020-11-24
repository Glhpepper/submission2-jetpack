package com.example.moviescatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.moviescatalogue.MyApplication
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.ui.main.MainActivity
import com.example.moviescatalogue.utils.DataBindingIdlingResource
import com.example.moviescatalogue.utils.DummyData
import com.example.moviescatalogue.utils.EspressoIdlingResource
import com.example.moviescatalogue.utils.monitorActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityOfflineTest {
    private val dummyMovies = DummyData.generateDummyMovies()
    private val detailDummyMovies = DummyData.generateDetail(dummyMovies[0].moviesId)

    private val dummyTvShows = DummyData.generateDummyShows()
    private val detailDummyTvShows = DummyData.generateDetail(dummyTvShows[0].showsId)

    private lateinit var mainRepository: MainRepository

    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun init() {
        mainRepository =
            ApplicationProvider.getApplicationContext<MyApplication>().appComponent.mainRepository
    }

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun loadOffline_MovieAndShows() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))

        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))

        activityScenario.close()
    }

    @Test
    fun loadDetailMoviesOffline() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        onView(withId(R.id.rv_movies))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        onView(withId(R.id.detail_image_offline)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_image_offline)).perform(ViewActions.swipeUp())

        onView(withId(R.id.title_offline)).check(matches(isDisplayed()))
        onView(withId(R.id.title_offline)).check(matches(withText(detailDummyMovies[0].detailTitle)))

        onView(withId(R.id.detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_overview)).check(matches(withText(detailDummyMovies[0].detailOverview)))

        onView(withId(R.id.text_director)).check(matches(isDisplayed()))
        onView(withId(R.id.text_director)).check(matches(withText("Director :")))

        onView(withId(R.id.detail_director)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_director)).check(matches(withText(detailDummyMovies[0].detailDirector)))

        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))

        onView(withId(R.id.text_score)).check(matches(isDisplayed()))
        onView(withId(R.id.text_score)).check(matches(withText("Score :")))

        onView(withId(R.id.detail_score)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_score)).check(matches(withText(detailDummyMovies[0].detailContentScoreText)))

        onView(withId(R.id.detail_top_cast)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_top_cast)).check(matches(withText("Top Cast :")))

        onView(withId(R.id.rv_top_cast)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_top_cast))
            .perform(detailDummyMovies[0].detailTopCast?.size?.let {
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    it
                )
            })

        activityScenario.close()
    }

    @Test
    fun loadDetailTvShowsOffline() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        onView(withId(R.id.detail_image_offline)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_image_offline)).perform(ViewActions.swipeUp())

        onView(withId(R.id.title_offline)).check(matches(isDisplayed()))
        onView(withId(R.id.title_offline)).check(matches(withText(detailDummyTvShows[0].detailTitle)))

        onView(withId(R.id.detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_overview)).check(matches(withText(detailDummyTvShows[0].detailOverview)))

        onView(withId(R.id.text_director)).check(matches(isDisplayed()))
        onView(withId(R.id.text_director)).check(matches(withText("Director :")))

        onView(withId(R.id.detail_director)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_director)).check(matches(withText(detailDummyTvShows[0].detailDirector)))

        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))

        onView(withId(R.id.text_score)).check(matches(isDisplayed()))
        onView(withId(R.id.text_score)).check(matches(withText("Score :")))

        onView(withId(R.id.detail_score)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_score)).check(matches(withText(detailDummyTvShows[0].detailContentScoreText)))

        onView(withId(R.id.detail_top_cast)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_top_cast)).check(matches(withText("Top Cast :")))

        onView(withId(R.id.rv_top_cast)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_top_cast))
            .perform(
                detailDummyTvShows[0].detailTopCast?.size?.let {
                    RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        it
                    )
                }
            )

        activityScenario.close()
    }
}