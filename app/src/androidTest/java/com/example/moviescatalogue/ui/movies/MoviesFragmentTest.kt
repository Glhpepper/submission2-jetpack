package com.example.moviescatalogue.ui.movies

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.moviescatalogue.R
import com.example.moviescatalogue.ui.main.MainActivity
import org.junit.Test
import org.junit.runner.RunWith


@MediumTest
@RunWith(AndroidJUnit4::class)
class MoviesFragmentTest {


    @Test
    fun moviesFragment_DisplayedInUi() {

        launchActivity()

        onView(withText("MOVIES")).check(matches(isDisplayed()))
        onView(withText("Movies Catalogue")).check(matches(isDisplayed()))
        onView(withText("TV SHOWS")).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
    }

    private fun launchActivity(): ActivityScenario<MainActivity>? {
        val activityScenario = launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            (activity.findViewById(R.id.rv_movies) as RecyclerView).itemAnimator = null
        }
        return activityScenario
    }
}