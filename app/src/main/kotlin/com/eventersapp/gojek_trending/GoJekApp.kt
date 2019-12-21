package com.eventersapp.gojek_trending

import android.app.Application
import com.eventersapp.gojek_trending.dagger.CoreComponent
import com.eventersapp.gojek_trending.dagger.CoreComponentProvider
import com.eventersapp.gojek_trending.dagger.DaggerCoreComponent
import timber.log.Timber

class GoJekApp : Application(), CoreComponentProvider {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(this)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    override fun provideCoreComponent(): CoreComponent = coreComponent
}