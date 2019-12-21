package com.eventersapp.gojek_trending.domain.trendingRepository

import com.eventersapp.gojek_trending.domain.TrendingModal
import com.eventersapp.gojek_trending.domain.baseUseCase.Result

interface TrendingDomainRepository {
    suspend fun getTrendingRepo(): Result<List<TrendingModal>>
}