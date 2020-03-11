package com.eventersapp.gojek_trending.ui.di

import androidx.lifecycle.ViewModel
import com.eventersapp.gojek_trending.dagger.ViewModelKey
import com.eventersapp.gojek_trending.dagger.scope.FragmentScope
import com.eventersapp.gojek_trending.data.dataSource.TrendingRemoteDataSource
import com.eventersapp.gojek_trending.data.repository.TrendingRemoteRepository
import com.eventersapp.gojek_trending.dataSource.TrendingApiService
import com.eventersapp.gojek_trending.dataSource.TrendingRemoteDataSourceImpl
import com.eventersapp.gojek_trending.dataSource.modal.remoteMapper.TrendingMapper
import com.eventersapp.gojek_trending.domain.trendingRepository.TrendingDomainRepository
import com.eventersapp.gojek_trending.domain.trendingUseCase.TrendingUseCase
import com.eventersapp.gojek_trending.ui.TrendingRepoViewModal
import com.eventersapp.gojek_trending.util.InternetChecker
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.create

@Module
abstract class TrendingModule {

    @Binds
    @IntoMap
    @ViewModelKey(TrendingRepoViewModal::class)
    abstract fun bindTrendingRepoViewModal(detailViewModel: TrendingRepoViewModal): ViewModel

    @Module
    companion object {

        @Provides
        @FragmentScope
        fun provideTrendingApiService(
            retrofit: Retrofit
        ): TrendingApiService {
            return retrofit.create()
        }

        @Provides
        @FragmentScope
        fun provideTrendingUseCase(
            trendingDomainRepository: TrendingDomainRepository
        ): TrendingUseCase {
            return TrendingUseCase(trendingDomainRepository)
        }

        @Provides
        @FragmentScope
        fun provideTrendingDomainRepository(
            trendingRemoteDataSource: TrendingRemoteDataSource
        ): TrendingDomainRepository {
            return TrendingRemoteRepository(trendingRemoteDataSource)
        }

        @Provides
        @FragmentScope
        fun provideTrendingRemoteDataSource(
            trendingApiService: TrendingApiService, trendingMapper: TrendingMapper
        ): TrendingRemoteDataSource {
            return TrendingRemoteDataSourceImpl(
                trendingApiService,
                trendingMapper
            )
        }
    }
}