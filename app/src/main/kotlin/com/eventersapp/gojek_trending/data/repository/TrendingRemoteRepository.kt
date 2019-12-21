package com.eventersapp.gojek_trending.data.repository

import com.eventersapp.gojek_trending.data.dataSource.TrendingRemoteDataSource
import com.eventersapp.gojek_trending.domain.TrendingModal
import com.eventersapp.gojek_trending.domain.baseUseCase.ErrorResult
import com.eventersapp.gojek_trending.domain.trendingRepository.TrendingDomainRepository
import com.eventersapp.gojek_trending.util.InternetChecker
import com.eventersapp.gojek_trending.domain.baseUseCase.Result
import javax.inject.Inject

class TrendingRemoteRepository @Inject constructor(
    private val trendingRemoteDataSource: TrendingRemoteDataSource,
    private val connectionDetector: InternetChecker
) : TrendingDomainRepository {
    override suspend fun getTrendingRepo(): Result<List<TrendingModal>> {
        return if (connectionDetector.isInternetAvailable)
            trendingRemoteDataSource.trendingRepo()
        else
            ErrorResult(message = "No internet connection.")
    }
}