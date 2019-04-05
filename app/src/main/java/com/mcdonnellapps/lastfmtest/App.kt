package com.mcdonnellapps.lastfmtest

import android.app.Application
import com.mcdonnellapps.lastfmtest.di.module.appModule
import com.mcdonnellapps.lastfmtest.di.module.feature.lastfm.lastFmModule
import com.mcdonnellapps.lastfmtest.di.module.ui.home.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)

            modules(
                appModule,
                lastFmModule,
                homeModule
            )
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}