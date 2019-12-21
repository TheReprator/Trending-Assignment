package com.eventersapp.gojek_trending.dagger

import com.eventersapp.gojek_trending.dagger.scope.AppScope
import com.eventersapp.gojek_trending.util.CoroutineDispatcherProvider
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [NetWorkModule::class, CoreModule::class])
@AppScope
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(): CoreComponent
    }

    val provideCoroutinesDispatcherProvider: CoroutineDispatcherProvider
    val provideRetrofit: Retrofit
}