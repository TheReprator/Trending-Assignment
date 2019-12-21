package com.eventersapp.gojek_trending.data.dataSource

import com.eventersapp.gojek_trending.domain.TrendingModal
import com.eventersapp.gojek_trending.domain.baseUseCase.Result

interface TrendingRemoteDataSource {
    suspend fun trendingRepo(): Result<List<TrendingModal>>

}