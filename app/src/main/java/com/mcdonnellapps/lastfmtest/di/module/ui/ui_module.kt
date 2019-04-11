package com.mcdonnellapps.lastfmtest.di.module.ui

import com.mcdonnellapps.lastfmtest.presentation.search.SearchPresenter
import com.mcdonnellapps.lastfmtest.ui.track.detail.TrackDetailPresenter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { SearchPresenter(get(), get(), get(), get()) }
    viewModel { TrackDetailPresenter(get(), get()) }
}