package com.example.moviescatalogue.utils

import android.view.View
import android.widget.HorizontalScrollView
import android.widget.ListView
import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf

class NestedScrollViewExtensionsSwipe(swipeAction: ViewAction = swipeUp()) :
    ViewAction by swipeAction {
    override fun getConstraints(): Matcher<View> {
        return allOf(
            withEffectiveVisibility(Visibility.VISIBLE),
            isDescendantOfA(
                anyOf(
                    isAssignableFrom(NestedScrollView::class.java),
                    isAssignableFrom(ScrollView::class.java),
                    isAssignableFrom(HorizontalScrollView::class.java),
                    isAssignableFrom(ListView::class.java)
                )
            )
        )
    }
}

class NestedScrollViewExtensionsScroll(scrollAction: ViewAction = scrollTo()) :
    ViewAction by scrollAction {
    override fun getConstraints(): Matcher<View> {
        return allOf(
            withEffectiveVisibility(Visibility.VISIBLE),
            isDescendantOfA(
                anyOf(
                    isAssignableFrom(NestedScrollView::class.java),
                    isAssignableFrom(ScrollView::class.java),
                    isAssignableFrom(HorizontalScrollView::class.java),
                    isAssignableFrom(ListView::class.java)
                )
            )
        )
    }
}