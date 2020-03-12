package com.eventersapp.gojek_trending.dagger

import android.content.Context
import com.eventersapp.gojek_trending.GoJekApp
import com.eventersapp.gojek_trending.dagger.scope.AppScope
import com.eventersapp.gojek_trending.ui.di.TrendingComponent
import com.eventersapp.gojek_trending.util.CoroutineDispatcherProvider
import com.eventersapp.gojek_trending.util.InternetChecker
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import retrofit2.Retrofit

@Component(modules = [NetWorkModule::class, CoreModule::class, SubmoduleComponent::class])
@AppScope
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: GoJekApp): CoreComponent
    }

    val provideCoroutinesDispatcherProvider: CoroutineDispatcherProvider
    val provideRetrofit: Retrofit
    val internetChecker: InternetChecker

    fun trendingComponent(): TrendingComponent.Factory
}

interface CoreComponentProvider {
    fun provideCoreComponent(): CoreComponent
}

fun Context.coreComponent() =
    (this.applicationContext as CoreComponentProvider).provideCoreComponent()

@Module(subcomponents = [TrendingComponent::class])
object SubmoduleComponent
