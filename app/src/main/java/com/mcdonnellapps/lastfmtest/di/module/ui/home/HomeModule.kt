package com.mcdonnellapps.lastfmtest.di.module.ui.home

import com.mcdonnellapps.lastfmtest.presentation.home.HomePresenter
import org.koin.dsl.module

val homeModule = module {
    factory {
        HomePresenter(get(), get(), get(), get())
    }
}