package com.mcdonnellapps.lastfmtest.di.module.feature.lastfm

import com.mcdonnellapps.lastfmtest.BuildConfig
import com.mcdonnellapps.lastfmtest.R
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.LastFmRepositoryImpl
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.SearchHistoryImpl
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.LastFmApiImpl
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.interceptor.ApiKeyInterceptor
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmRepository
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.SearchHistory
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.api.LastFmApi
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.interactor.AddRecentQuery
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.interactor.GetRecentQueries
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.experimental.builder.factory
import org.koin.experimental.builder.singleBy

val lastFmModule = module {
    single(named("api-url")) {
        androidContext().getString(R.string.lastfm_api_url)
    }

    single {
        ApiKeyInterceptor(BuildConfig.LASTFM_API_KEY)
    }

    single<LastFmApi> {
        LastFmApiImpl(
            apiUrl = get(named("api-url")),
            apiKeyInterceptor = get()
        )
    }

    // Repositories
    singleBy<LastFmRepository, LastFmRepositoryImpl>()

    // Services
    singleBy<SearchHistory, SearchHistoryImpl>()

    // Interactors
    factory<GetRecentQueries>()
    factory<AddRecentQuery>()
}