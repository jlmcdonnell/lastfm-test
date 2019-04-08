package com.mcdonnellapps.lastfmtest.di.module.ui

import com.mcdonnellapps.lastfmtest.presentation.search.SearchPresenter
import com.mcdonnellapps.lastfmtest.ui.track.detail.TrackDetailPresenter
import org.koin.dsl.module
import org.koin.experimental.builder.factory

val uiModule = module {
    factory<SearchPresenter>()
    factory<TrackDetailPresenter>()
}