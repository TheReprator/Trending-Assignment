package com.eventersapp.gojek_trending.domain.trendingUseCase

import com.eventersapp.gojek_trending.domain.TrendingModal
import com.eventersapp.gojek_trending.domain.baseUseCase.UseCase
import com.eventersapp.gojek_trending.domain.baseUseCase.Result
import com.eventersapp.gojek_trending.domain.trendingRepository.TrendingDomainRepository

class TrendingUseCase(private val trendingRepository: TrendingDomainRepository) :
    UseCase<TrendingModal, Unit> {
    override suspend fun run(params: Unit): Result<TrendingModal> =
        trendingRepository.getTrendingRepo()
}
