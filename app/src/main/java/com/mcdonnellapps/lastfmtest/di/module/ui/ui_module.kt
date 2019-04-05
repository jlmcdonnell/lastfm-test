package com.mcdonnellapps.lastfmtest.di.module.ui

import com.mcdonnellapps.lastfmtest.presentation.home.HomePresenter
import com.mcdonnellapps.lastfmtest.ui.track.detail.TrackDetailPresenter
import org.koin.dsl.module
import org.koin.experimental.builder.factory

val uiModule = module {
    factory<HomePresenter>()
    factory<TrackDetailPresenter>()
}