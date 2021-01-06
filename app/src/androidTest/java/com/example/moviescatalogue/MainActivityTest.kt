package com.example.moviescatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.moviescatalogue.data.MainRepository
import com.example.moviescatalogue.ui.main.MainActivity
import com.example.moviescatalogue.utils.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityOnlineTest {
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
    fun loadOnline_MoviesAndShows() {
        val moviesEntity = runBlocking {
            mainRepository.getMoviesApi()
        }
        val showsEntity = runBlocking {
            mainRepository.getShowsApi()
        }

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        onView(withId(R.id.rv_movies))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies))
            .perform(moviesEntity.value?.results?.size?.let {
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    it
                )
            })

        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_tv_shows))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows))
            .perform(showsEntity.value?.results?.size?.let {
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    it
                )
            })

        activityScenario.close()
    }

    @Test
    fun loadDetailMoviesOnline() {
        val movies = runBlocking {
            mainRepository.getMoviesApi()
        }
        val detailEntity = movies.value?.results?.get(0)?.moviesId.let { id ->
            runBlocking {
                id?.let { mainRepository.getDetailMovie(it) }
            }
        }

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        onView(withId(R.id.rv_movies))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        onView(withId(R.id.movies_title)).check(matches(isDisplayed()))
        onView(withId(R.id.movies_title)).check(matches(withText(detailEntity?.value?.title)))

        onView(withId(R.id.detail_image_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_image_movie)).perform(swipeUp())

        onView(withId(R.id.detail_overview_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_overview_movies)).check(matches(withText(detailEntity?.value?.overview)))

        onView(withId(R.id.homepage_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.homepage_movies)).check(matches(withText(detailEntity?.value?.homepage)))

        onView(withId(R.id.genre_movies)).check(matches(isDisplayed()))
        val genre = detailEntity?.value?.genres?.map { it.name }
        onView(withId(R.id.genre_movies)).check(matches(withText(genre?.joinToString(separator = " - "))))

        onView(withId(R.id.status_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.status_movies)).check(matches(withText(detailEntity?.value?.status)))

        onView(withId(R.id.date_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.date_movies)).check(matches(withText(detailEntity?.value?.releaseDate)))

        onView(withId(R.id.popularity_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.popularity_movies)).check(matches(withText(detailEntity?.value?.popularity.toString())))

        onView(withId(R.id.vote_average_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.vote_average_movie)).check(matches(withText(detailEntity?.value?.voteAverage.toString())))

        onView(withId(R.id.vote_count_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.vote_count_movie)).check(matches(withText(detailEntity?.value?.voteCount.toString())))

        onView(withId(R.id.original_title_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.original_title_movies)).check(matches(withText(detailEntity?.value?.originalTitle)))

        onView(withId(R.id.tagline_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.tagline_movie)).check(matches(withText(detailEntity?.value?.tagline)))

        onView(withId(R.id.backdrop_movie)).check(matches(isDisplayed()))

        activityScenario.close()
    }

    @Test
    fun saveMovieToFavorite_thenDelete() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        onView(withId(R.id.rv_movies))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        onView(withId(R.id.detail_image_movie)).perform(swipeUp())
        onView(withId(R.id.backdrop_movie)).perform(NestedScrollViewExtensionsSwipe())

        onView(withId(R.id.fab_movies)).perform(click())
        pressBack()

        onView(withId(R.id.favorite_menu)).perform(click())

        onView(withId(R.id.rv_favorite_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_movies))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        onView(withId(R.id.detail_image_movie)).perform(swipeUp())
        onView(withId(R.id.backdrop_movie)).perform(NestedScrollViewExtensionsSwipe())

        onView(withId(R.id.fab_movies)).perform(click())
        pressBack()

        onView(withId(R.id.imageNoFavorite_movies)).check(matches(isDisplayed()))

        activityScenario.close()
    }

    @Test
    fun saveMovieToFavorite_thenSwipeDelete() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        onView(withId(R.id.rv_movies))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        onView(withId(R.id.detail_image_movie)).perform(swipeUp())
        onView(withId(R.id.backdrop_movie)).perform(NestedScrollViewExtensionsSwipe())

        onView(withId(R.id.fab_movies)).perform(click())
        pressBack()

        onView(withId(R.id.favorite_menu)).perform(click())

        onView(withId(R.id.rv_favorite_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.img_movies)).perform(swipeRight())

        onView(withId(R.id.imageNoFavorite_movies)).check(matches(isDisplayed()))

        activityScenario.close()
    }

    @Test
    fun loadDetailShowsOnline() {
        val shows = runBlocking {
            mainRepository.getShowsApi()
        }
        val detailEntity = shows.value?.results?.get(0)?.showsId.let { id ->
            runBlocking {
                id?.let { mainRepository.getDetailTvShows(it) }
            }
        }

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
        onView(withId(R.id.shows_title)).check(matches(isDisplayed()))
        onView(withId(R.id.shows_title)).check(matches(withText(detailEntity?.value?.name)))

        onView(withId(R.id.detail_image_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_image_shows)).perform(swipeUp())

        onView(withId(R.id.detail_overview_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_overview_shows)).check(matches(withText(detailEntity?.value?.overview)))

        onView(withId(R.id.homepage_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.homepage_shows)).check(matches(withText(detailEntity?.value?.homepage)))

        onView(withId(R.id.genre_shows)).check(matches(isDisplayed()))
        val genre = detailEntity?.value?.genres?.map { it.name }
        onView(withId(R.id.genre_shows)).check(matches(withText(genre?.joinToString(separator = " - "))))

        onView(withId(R.id.status_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.status_shows)).check(matches(withText(detailEntity?.value?.status)))

        onView(withId(R.id.date_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.date_shows)).check(matches(withText(detailEntity?.value?.firstAirDate)))

        onView(withId(R.id.popularity_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.popularity_shows)).check(matches(withText(detailEntity?.value?.popularity.toString())))

        onView(withId(R.id.vote_average_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.vote_average_shows)).check(matches(withText(detailEntity?.value?.voteAverage.toString())))

        onView(withId(R.id.vote_count_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.vote_count_shows)).check(matches(withText(detailEntity?.value?.voteCount.toString())))

        onView(withId(R.id.original_title_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.original_title_shows)).check(matches(withText(detailEntity?.value?.originalName)))

        onView(withId(R.id.tagline_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.tagline_shows)).check(matches(withText(detailEntity?.value?.tagline)))

        onView(withId(R.id.rv_season_list)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_season_list))
            .perform(detailEntity?.value?.seasons?.size?.let {
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    it
                )
            })

        activityScenario.close()
    }

    @Test
    fun saveShowToFavorite_thenDelete() {
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

        onView(withId(R.id.detail_image_shows)).perform(swipeUp())
        onView(withId(R.id.rv_season_list)).perform(NestedScrollViewExtensionsScroll())

        onView(withId(R.id.fab_shows)).perform(click())
        pressBack()

        onView(withId(R.id.favorite_menu)).perform(click())

        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_favorite_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite_shows))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )
        onView(withId(R.id.detail_image_shows)).perform(swipeUp())
        onView(withId(R.id.rv_season_list)).perform(NestedScrollViewExtensionsScroll())

        onView(withId(R.id.fab_shows)).perform(click())
        pressBack()

        onView(withId(R.id.imageNoFavorite_shows)).check(matches(isDisplayed()))

        activityScenario.close()
    }

    @Test
    fun saveShowToFavorite_thenSwipeDelete() {
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

        onView(withId(R.id.detail_image_shows)).perform(swipeUp())
        onView(withId(R.id.rv_season_list)).perform(NestedScrollViewExtensionsScroll())

        onView(withId(R.id.fab_shows)).perform(click())
        pressBack()

        onView(withId(R.id.favorite_menu)).perform(click())

        onView(withText("Tv Shows")).perform(click())
        onView(withId(R.id.rv_favorite_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.img_shows)).perform(swipeLeft())

        onView(withId(R.id.imageNoFavorite_shows)).check(matches(isDisplayed()))

        activityScenario.close()
    }
}