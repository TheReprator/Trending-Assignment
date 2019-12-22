package com.eventersapp.gojek_trending.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.eventersapp.gojek_trending.domain.TrendingModal
import com.eventersapp.gojek_trending.domain.baseUseCase.Success
import com.eventersapp.gojek_trending.domain.trendingUseCase.TrendingUseCase
import com.eventersapp.gojek_trending.ui.fakeData.TrendingRepo
import com.eventersapp.gojek_trending.util.CoroutinesDispatcherImpl
import com.eventersapp.gojek_trending.util.event.Event
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Unconfined
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.concurrent.Executors

@RunWith(JUnit4::class)
class TrendingRepoViewModalTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val trendingUseCase = mockk<TrendingUseCase>(relaxed = true)
    private val appDispatchers = CoroutinesDispatcherImpl(
        Dispatchers.Main, Dispatchers.IO, Dispatchers.IO, Dispatchers.Default,
        Executors.newFixedThreadPool(1).asCoroutineDispatcher()
    )

    private lateinit var trendingRepoViewModal: TrendingRepoViewModal

    @Before
    fun initTests() {
        Dispatchers.setMain(Unconfined)
    }

    @Test
    fun `Trending Repo requested when ViewModel is created`() {
        val observerData = mockk<Observer<List<TrendingModal>>>(relaxed = true)
        val observerLoader = mockk<Observer<Event<Boolean>>>(relaxed = true)

        val result = TrendingRepo.FAKE_REPO
        coEvery {
            trendingUseCase.run(Unit)
        } returns Success<List<TrendingModal>>(result)

        trendingRepoViewModal = TrendingRepoViewModal(appDispatchers, trendingUseCase)
        trendingRepoViewModal.isLoading.observeForever(observerLoader)
        trendingRepoViewModal.trendingList.observeForever(observerData)

        verifySequence {
            observerLoader.onChanged(Event(true))
            observerLoader.onChanged(Event(false))
            observerData.onChanged(result)
        }
        confirmVerified(observerData)
    }

    @Test
    fun `Trending Repo requested but failed when ViewModel is created`() {
        val observerData = mockk<Observer<List<TrendingModal>>>(relaxed = true)
        val observerLoader = mockk<Observer<Event<Boolean>>>(relaxed = true)
        val observerError = mockk<Observer<Event<Boolean>>>(relaxed = true)

        val result = TrendingRepo.FAKE_ERROR
        coEvery {
            trendingUseCase.run(Unit)
        } returns result

        trendingRepoViewModal = TrendingRepoViewModal(appDispatchers, trendingUseCase)
        trendingRepoViewModal.isLoading.observeForever(observerLoader)
        trendingRepoViewModal.isError.observeForever(observerError)
        trendingRepoViewModal.trendingList.observeForever(observerData)

        verifySequence {
            observerLoader.onChanged(Event(true))
            observerLoader.onChanged(Event(false))
            observerError.onChanged(Event(true))
        }

        verify(exactly = 0)
        {
            observerData.onChanged(TrendingRepo.FAKE_REPO)
        }

        confirmVerified(observerData)
    }

    @Test
    fun `Trending Repo requested With Pull To Refresh`() {
        val observerData = mockk<Observer<List<TrendingModal>>>(relaxed = true)
        val observerLoader = mockk<Observer<Event<Boolean>>>(relaxed = true)

        val result = TrendingRepo.FAKE_REPO
        coEvery {
            trendingUseCase.run(Unit)
        } returns Success<List<TrendingModal>>(result)

        trendingRepoViewModal = TrendingRepoViewModal(appDispatchers, trendingUseCase)
        trendingRepoViewModal.isLoading.observeForever(observerLoader)
        trendingRepoViewModal.trendingList.observeForever(observerData)

        trendingRepoViewModal.getTrendingUseCaseWithPullToRefresh()

        verifySequence {
            observerLoader.onChanged(Event(true))
            observerLoader.onChanged(Event(false))
            observerData.onChanged(result)

            observerLoader.onChanged(Event(false))
            observerData.onChanged(result)
        }
        confirmVerified(observerData)
    }

    @Test
    fun `Trending Repo requested With Pull To Refresh but Failed`() {
        val observerData = mockk<Observer<List<TrendingModal>>>(relaxed = true)
        val observerLoader = mockk<Observer<Event<Boolean>>>(relaxed = true)
        val observerError = mockk<Observer<Event<Boolean>>>(relaxed = true)

        val result = TrendingRepo.FAKE_ERROR
        coEvery {
            trendingUseCase.run(Unit)
        } returns result

        trendingRepoViewModal = TrendingRepoViewModal(appDispatchers, trendingUseCase)
        trendingRepoViewModal.isLoading.observeForever(observerLoader)
        trendingRepoViewModal.isError.observeForever(observerError)
        trendingRepoViewModal.trendingList.observeForever(observerData)

        trendingRepoViewModal.getTrendingUseCaseWithPullToRefresh()

        verifySequence {
            observerLoader.onChanged(Event(true))
            observerLoader.onChanged(Event(false))
            observerError.onChanged(Event(true))

            observerLoader.onChanged(Event(false))
            observerError.onChanged(Event(true))
        }

        verify(exactly = 0)
        {
            observerData.onChanged(TrendingRepo.FAKE_REPO)
        }

        confirmVerified(observerData)
    }

    @Test
    fun `Trending Repo requested with retry`() {
        val observerData = mockk<Observer<List<TrendingModal>>>(relaxed = true)

        val result = TrendingRepo.FAKE_REPO
        coEvery {
            trendingUseCase.run(Unit)
        } returns Success<List<TrendingModal>>(result)

        trendingRepoViewModal = TrendingRepoViewModal(appDispatchers, trendingUseCase)
        trendingRepoViewModal.trendingList.observeForever(observerData)

        trendingRepoViewModal.retry()

        verify(exactly = 2) { observerData.onChanged(result) }

        confirmVerified(observerData)

        Assert.assertEquals(result, trendingRepoViewModal.trendingList.value)
    }

    @Test
    fun `Trending Repo requested but failed with retry`() {
        val observerData = mockk<Observer<List<TrendingModal>>>(relaxed = true)
        val observerError = mockk<Observer<Event<Boolean>>>(relaxed = true)

        val result = TrendingRepo.FAKE_ERROR
        coEvery {
            trendingUseCase.run(Unit)
        } returns result

        trendingRepoViewModal = TrendingRepoViewModal(appDispatchers, trendingUseCase)
        trendingRepoViewModal.isError.observeForever(observerError)
        trendingRepoViewModal.trendingList.observeForever(observerData)

        trendingRepoViewModal.retry()

        verify(exactly = 2)
        {
            observerError.onChanged(Event(true))
        }

        verify(exactly = 0) { observerData.onChanged(TrendingRepo.FAKE_REPO) }

        confirmVerified(observerData)
    }
}