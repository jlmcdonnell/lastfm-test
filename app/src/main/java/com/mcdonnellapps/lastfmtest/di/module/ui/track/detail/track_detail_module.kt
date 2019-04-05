package com.mcdonnellapps.lastfmtest.di.module.ui.track.detail

import com.mcdonnellapps.lastfmtest.ui.track.detail.TrackDetailPresenter
import org.koin.dsl.module

val trackDetailModule = module {
    factory { TrackDetailPresenter(get(), get()) }
}