package com.mcdonnellapps.lastfmtest

import android.app.Application
import com.mcdonnellapps.lastfmtest.di.module.appModule
import com.mcdonnellapps.lastfmtest.di.module.feature.lastfm.lastFmModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)

            modules(
                appModule,
                lastFmModule
            )
        }
    }

}