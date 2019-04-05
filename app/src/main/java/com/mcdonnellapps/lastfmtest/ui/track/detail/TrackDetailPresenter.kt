package com.mcdonnellapps.lastfmtest.ui.track.detail

import com.mcdonnellapps.lastfmtest.common.AppExecutors
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmRepository
import com.mcdonnellapps.lastfmtest.presenter.base.BasePresenter
import com.mcdonnellapps.lastfmtest.presenter.base.BaseView
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TrackDetailPresenter(
    private val appExecutors: AppExecutors,
    private val lastFmRepository: LastFmRepository
) : BasePresenter<TrackDetailPresenter.View>(appExecutors) {

    lateinit var trackId: String

    override fun bind(view: View) {
        super.bind(view)
        uiScope.launch {
            withContext(appExecutors.io) {
                // get track
            }
        }
    }

    interface View : BaseView
}