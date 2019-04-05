package com.mcdonnellapps.lastfmtest.di.module

import com.mcdonnellapps.lastfmtest.common.AppExecutors
import org.koin.dsl.module

val appModule = module {

    @Suppress("RemoveExplicitTypeArguments")
    single<AppExecutors> { AppExecutors() }
}