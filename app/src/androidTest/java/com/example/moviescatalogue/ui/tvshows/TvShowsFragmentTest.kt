package com.example.moviescatalogue.ui.tvshows

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.moviescatalogue.R
import com.example.moviescatalogue.ui.main.MainActivity
import org.junit.Test
import org.junit.runner.RunWith


@MediumTest
@RunWith(AndroidJUnit4::class)
class TvShowsFragmentTest {

    @Test
    fun tvShowsFragment_DisplayedInUi() {

        launchActivity()

        onView(withText("TV SHOWS")).perform(click())
        onView(withText("MOVIES")).check(matches(isDisplayed()))
        onView(withText("Movies Catalogue")).check(matches(isDisplayed()))
        onView(withText("TV SHOWS")).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
    }


    private fun launchActivity(): ActivityScenario<MainActivity>? {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { activity ->
            (activity.findViewById(R.id.rv_tv_shows) as RecyclerView).itemAnimator = null
        }
        return activityScenario
    }
}