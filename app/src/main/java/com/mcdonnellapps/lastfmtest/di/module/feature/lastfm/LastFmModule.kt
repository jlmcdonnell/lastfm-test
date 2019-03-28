package com.mcdonnellapps.lastfmtest.di.module.feature.lastfm

import com.mcdonnellapps.lastfmtest.BuildConfig
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.LastFmApiImpl
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.LastFmRepositoryImpl
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmApi
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmRepository
import org.koin.dsl.module

val lastFmModule = module {
    single<LastFmApi> { LastFmApiImpl(BuildConfig.LASTFM_API_KEY) }

    single<LastFmRepository> {
        LastFmRepositoryImpl(get())
    }
}