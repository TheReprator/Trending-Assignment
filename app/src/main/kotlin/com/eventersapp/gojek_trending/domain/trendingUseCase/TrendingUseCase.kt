package com.eventersapp.gojek_trending.domain.trendingUseCase

import com.eventersapp.gojek_trending.domain.TrendingModal
import com.eventersapp.gojek_trending.domain.baseUseCase.UseCase
import com.eventersapp.gojek_trending.domain.baseUseCase.Result
import com.eventersapp.gojek_trending.domain.trendingRepository.TrendingDomainRepository
import javax.inject.Inject

class TrendingUseCase @Inject constructor (private val trendingRepository: TrendingDomainRepository) :
    UseCase<List<TrendingModal>, Unit> {
    override suspend fun run(params: Unit): Result<List<TrendingModal>> =
        trendingRepository.getTrendingRepo()
}
