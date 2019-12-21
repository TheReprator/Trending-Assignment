package com.eventersapp.gojek_trending.dagger

import com.eventersapp.gojek_trending.dagger.scope.AppScope
import com.eventersapp.gojek_trending.util.CoroutineDispatcherProvider
import com.eventersapp.gojek_trending.util.CoroutinesDispatcherImpl
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

@Module
class CoreModule {

    @Provides
    @AppScope
    fun provideCoroutineDispatcherProvider(): CoroutineDispatcherProvider {
        return CoroutinesDispatcherImpl(
            Main, IO, IO, Default, Executors.newFixedThreadPool(1).asCoroutineDispatcher()
        )
    }

}

