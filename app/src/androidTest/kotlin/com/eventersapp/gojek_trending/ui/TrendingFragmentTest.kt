package com.eventersapp.gojek_trending.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Observer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.eventersapp.gojek_trending.MainActivity
import com.eventersapp.gojek_trending.R
import com.eventersapp.gojek_trending.domain.TrendingModal
import com.eventersapp.gojek_trending.domain.baseUseCase.Success
import com.eventersapp.gojek_trending.domain.trendingUseCase.TrendingUseCase
import com.eventersapp.gojek_trending.util.CoroutinesDispatcherImpl
import com.eventersapp.gojek_trending.util.EspressoTestUtil
import com.eventersapp.gojek_trending.util.fakeData.TrendingRepo.FAKE_REPO
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executors

@RunWith(AndroidJUnit4::class)
class TrendingFragmentTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(MainActivity::class.java, true, true)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var trendingRepoViewModal: TrendingRepoViewModal

    private val trendingUseCase = mockk<TrendingUseCase>(relaxed = true)
    private val dispatcher = CoroutinesDispatcherImpl(
        Dispatchers.Main, Dispatchers.IO, Dispatchers.IO, Dispatchers.Default,
        Executors.newFixedThreadPool(1).asCoroutineDispatcher()
    )

    val observerData = mockk<Observer<List<TrendingModal>>>(relaxed = true)


    @Before
    fun init() {
        Dispatchers.resetMain()

        activityRule.runOnUiThread {
            trendingRepoViewModal = TrendingRepoViewModal(dispatcher, trendingUseCase)
        }

        EspressoTestUtil.disableProgressBarAnimations(activityRule)
    }

    @Test
    fun isProgressBarDisplayed() {
       // mockUseCaseForSingleItem()
        val scenario = launchFragmentInContainer<TrendingFragment>()
        onView(withId(R.id.fragTrend_pb)).check(matches(isDisplayed()))
    }

    private fun mockUseCaseForSingleItem(): TrendingUseCase {
        val collectionUseCase = mockk<TrendingUseCase>()

        coEvery {
                collectionUseCase.run(Unit)
        } returns Success<List<TrendingModal>>(FAKE_REPO)
        return collectionUseCase
    }
}