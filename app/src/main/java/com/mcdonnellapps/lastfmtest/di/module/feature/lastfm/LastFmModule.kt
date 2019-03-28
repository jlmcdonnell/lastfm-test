package com.mcdonnellapps.lastfmtest.di.module.feature.lastfm

import com.mcdonnellapps.lastfmtest.BuildConfig
import com.mcdonnellapps.lastfmtest.R
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.LastFmRepositoryImpl
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.LastFmApiImpl
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.interceptor.ApiKeyInterceptor
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmRepository
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.api.LastFmApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

val lastFmModule = module {
    val apiUrlQualifier = StringQualifier("last-fm-api-url")

    single(apiUrlQualifier) {
        androidContext().getString(R.string.lastfm_api_url)
    }

    single {
        ApiKeyInterceptor(BuildConfig.LASTFM_API_KEY)
    }

    single {
        LastFmApiImpl(
            apiUrl = get(apiUrlQualifier),
            apiKeyInterceptor = get()
        ) as LastFmApi
    }

    single {
        LastFmApiImpl(
            apiUrl = get(apiUrlQualifier),
            apiKeyInterceptor = get()
        )
    }

    single<LastFmRepository> {
        LastFmRepositoryImpl(get())
    }
}