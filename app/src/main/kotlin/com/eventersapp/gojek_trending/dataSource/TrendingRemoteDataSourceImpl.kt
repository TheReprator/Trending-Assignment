package com.eventersapp.gojek_trending.dataSource

import com.eventersapp.gojek_trending.data.dataSource.TrendingRemoteDataSource
import com.eventersapp.gojek_trending.dataSource.modal.remoteMapper.TrendingMapper
import com.eventersapp.gojek_trending.domain.TrendingModal
import com.eventersapp.gojek_trending.util.safeApiCall
import com.eventersapp.gojek_trending.domain.baseUseCase.Result
import com.eventersapp.gojek_trending.domain.baseUseCase.toListMapper
import com.eventersapp.gojek_trending.util.toResult
import javax.inject.Inject

class TrendingRemoteDataSourceImpl @Inject constructor(
    private val trendingApiService: TrendingApiService, private val trendingMapper: TrendingMapper
) : TrendingRemoteDataSource {


    private suspend fun getTrendingRepoRemote(): Result<List<TrendingModal>> {
        return trendingApiService.fetchTrendingRepo().toResult(trendingMapper.toListMapper())
    }

    override suspend fun trendingRepo(): Result<List<TrendingModal>> =
        safeApiCall(call = { getTrendingRepoRemote() })

}
