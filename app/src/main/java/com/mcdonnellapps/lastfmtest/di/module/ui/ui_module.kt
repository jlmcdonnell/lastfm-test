package com.mcdonnellapps.lastfmtest.di.module.ui

import com.mcdonnellapps.lastfmtest.di.module.ui.home.homeModule
import com.mcdonnellapps.lastfmtest.di.module.ui.track.detail.trackDetailModule
import org.koin.dsl.module

val uiModule = module {
    homeModule
    trackDetailModule
}