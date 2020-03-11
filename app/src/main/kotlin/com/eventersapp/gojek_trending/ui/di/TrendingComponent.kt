package com.eventersapp.gojek_trending.ui.di

import com.eventersapp.gojek_trending.dagger.scope.FragmentScope
import com.eventersapp.gojek_trending.ui.TrendingFragment
import dagger.Subcomponent

@Subcomponent(modules = [TrendingModule::class])
@FragmentScope
interface TrendingComponent {

    fun inject(target: TrendingFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): TrendingComponent
    }
}
