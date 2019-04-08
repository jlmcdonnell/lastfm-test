package com.mcdonnellapps.lastfmtest.di.module

import com.mcdonnellapps.lastfmtest.common.AppExecutors
import com.mcdonnellapps.lastfmtest.data.feature.common.preferences.AppPreferencesImpl
import com.mcdonnellapps.lastfmtest.domain.feature.common.preferences.AppPreferences
import com.mcdonnellapps.lastfmtest.domain.feature.common.preferences.interactor.AddRecentQuery
import com.mcdonnellapps.lastfmtest.domain.feature.common.preferences.interactor.RecentQueries
import org.koin.dsl.module
import org.koin.experimental.builder.single
import org.koin.experimental.builder.singleBy

val appModule = module {
    @Suppress("RemoveExplicitTypeArguments")
    single<AppExecutors> { AppExecutors() }


    // Services
    singleBy<AppPreferences, AppPreferencesImpl>()

    // Interactors
    single<AddRecentQuery>()
    single<RecentQueries>()
}