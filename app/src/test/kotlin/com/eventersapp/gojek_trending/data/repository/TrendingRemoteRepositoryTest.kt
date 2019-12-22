package com.eventersapp.gojek_trending.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.eventersapp.gojek_trending.data.dataSource.TrendingRemoteDataSource
import com.eventersapp.gojek_trending.domain.TrendingModal
import com.eventersapp.gojek_trending.domain.baseUseCase.Success
import com.eventersapp.gojek_trending.ui.TrendingRepoViewModal
import com.eventersapp.gojek_trending.ui.fakeData.TrendingRepo.FAKE_ERROR
import com.eventersapp.gojek_trending.ui.fakeData.TrendingRepo.FAKE_REPO
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TrendingRemoteRepositoryTest{

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var trendingRemoteRepository: TrendingRemoteRepository
    val trendingRemoteDataSource = mockk<TrendingRemoteDataSource>(relaxed = true)

    @Before
    fun init()
    {
        trendingRemoteRepository =  TrendingRemoteRepository(trendingRemoteDataSource)
    }

    @Test
    fun `Fetch Trending Repo`(){
        coEvery {
            trendingRemoteDataSource.trendingRepo()
        }returns Success(FAKE_REPO)

        runBlocking {
            trendingRemoteRepository.getTrendingRepo()

            assertEquals(trendingRemoteDataSource.trendingRepo(), Success(FAKE_REPO))
        }
    }

    @Test
    fun `Fetch Trending Repo failed`(){
        coEvery {
            trendingRemoteDataSource.trendingRepo()
        }returns FAKE_ERROR

        runBlocking {
            trendingRemoteRepository.getTrendingRepo()

            assertEquals(trendingRemoteDataSource.trendingRepo(), FAKE_ERROR)
        }
    }
}