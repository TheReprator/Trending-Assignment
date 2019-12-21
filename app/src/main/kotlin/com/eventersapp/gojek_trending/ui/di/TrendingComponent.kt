package com.eventersapp.gojek_trending.ui.di

import com.eventersapp.gojek_trending.dagger.CoreComponent
import com.eventersapp.gojek_trending.dagger.scope.FragmentScope
import com.eventersapp.gojek_trending.ui.TrendingFragment
import dagger.Component

@Component(
    modules = [ConnectModule::class],
    dependencies = [CoreComponent::class]
)
@FragmentScope
interface TrendingComponent {

    fun inject(target: TrendingFragment)

    @Component.Factory
    interface Factory {
        fun create(dependency: CoreComponent): TrendingComponent
    }
}
