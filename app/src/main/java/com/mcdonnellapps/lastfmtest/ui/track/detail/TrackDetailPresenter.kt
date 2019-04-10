package com.mcdonnellapps.lastfmtest.ui.track.detail

import com.mcdonnellapps.lastfmtest.common.AppExecutors
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFMException
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFMException.Error.*
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmRepository
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.TrackInfo
import com.mcdonnellapps.lastfmtest.presenter.base.BasePresenter
import com.mcdonnellapps.lastfmtest.presenter.base.BaseView
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class TrackDetailPresenter(
    private val appExecutors: AppExecutors,
    private val lastFmRepository: LastFmRepository
) : BasePresenter<TrackDetailPresenter.View>(appExecutors) {

    lateinit var trackId: String

    override fun bind(view: View) {
        super.bind(view)
        loadTrack()
    }

    private fun loadTrack() {
        uiScope.launch {
            withContext(appExecutors.io) {
                try {
                    view?.showTrackInfo(lastFmRepository.trackById(trackId))
                } catch (e: Exception) {
                    when ((e as? LastFMException)?.error) {
                        TrackNotFoundError -> Timber.d("Unable to find track ($trackId)")
                    }
                    view?.showGenericError()
                }
            }
        }
    }

    interface View : BaseView {
        fun showTrackInfo(trackInfo: TrackInfo)
    }
}