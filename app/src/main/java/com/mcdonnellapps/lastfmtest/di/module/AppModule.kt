package com.mcdonnellapps.lastfmtest.di.module

import com.mcdonnellapps.lastfmtest.common.AppExecutors
import com.mcdonnellapps.lastfmtest.presentation.home.HomePresenter
import org.koin.dsl.module

val appModule = module {

    @Suppress("RemoveExplicitTypeArguments")
    single<AppExecutors> { AppExecutors() }

    factory { HomePresenter(get(), get()) }
}