package com.example.moviescatalogue.ui.main

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.moviescatalogue.R
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@RunWith(AndroidJUnit4::class)
class MainFragmentTest {

    @Test
    fun mainFragment_DisplayedInUi() {

        launchActivity()

        onView(withId(R.id.pb_main)).check(matches(isDisplayed()))
        onView(withId(R.id.appbar)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.app_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tab_layout)).check(matches(isDisplayed()))
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
    }

    private fun launchActivity(): ActivityScenario<MainActivity>? {
        return ActivityScenario.launch(MainActivity::class.java)
    }
}