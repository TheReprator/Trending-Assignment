package com.eventersapp.gojek_trending.dataSource

import com.eventersapp.gojek_trending.dataSource.modal.TrendingRemoteModal
import retrofit2.Response
import retrofit2.http.POST

interface TrendingApiService {
    @POST("repositories")
    suspend fun fetchTrendingRepo(): Response<TrendingRemoteModal>
}