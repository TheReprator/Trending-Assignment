package com.eventersapp.gojek_trending.ui

import android.view.View
import androidx.annotation.NonNull
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.eventersapp.gojek_trending.MainActivity
import com.eventersapp.gojek_trending.R
import com.eventersapp.gojek_trending.TrendingFragmentTestSuite
import com.eventersapp.gojek_trending.util.EspressoIdlingResource
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Runs [TrendingFragmentTestSuite], not this.
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class TrendingFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, true, true)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun is_ProgressBar_Gone_After_Data_Being_Loaded() {
        val scenario = launchFragmentInContainer<TrendingFragment>()
        onView(withId(R.id.fragTrend_pb)).check(matches(not(isDisplayed())))
        scenario.moveToState(Lifecycle.State.DESTROYED)
    }

    @Test
    fun is_RecyclerView_Visible_After_Data_Being_Loaded() {
        val scenario = launchFragmentInContainer<TrendingFragment>()
        onView(withId(R.id.fragTrend_recy)).check(matches(isDisplayed()))
        scenario.moveToState(Lifecycle.State.DESTROYED)
    }

    @Test
    fun click_The_Corresponding_Item() {
        val scenario = launchFragmentInContainer<TrendingFragment>()
        onView(withId(R.id.fragTrend_recy))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<VHTrendingRepo>(0, click())
            )
        scenario.moveToState(Lifecycle.State.DESTROYED)
    }

    @Test
    fun given_Text_Matches_With_The_Item_At_Specific_Position() {
        val scenario = launchFragmentInContainer<TrendingFragment>()
        onView(withId(R.id.fragTrend_recy))
            .perform(RecyclerViewActions.scrollToPosition<VHTrendingRepo>(23))
            .check(matches(atPositionOnView(23, withText("google"), R.id.trend_tv_userName)))
        scenario.moveToState(Lifecycle.State.DESTROYED)
    }

    @Suppress("SameParameterValue")
    private fun atPositionOnView(
        position: Int, itemMatcher: Matcher<View>,
        @NonNull targetViewId: Int
    ): Matcher<View?> {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has view id $itemMatcher at position $position")
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder =
                    recyclerView.findViewHolderForAdapterPosition(position)
                val targetView: View = viewHolder!!.itemView.findViewById(targetViewId)
                return itemMatcher.matches(targetView)
            }
        }
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }
}
