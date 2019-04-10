package com.mcdonnellapps.lastfmtest.ui.track.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mcdonnellapps.lastfmtest.R
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.TrackInfo
import org.koin.android.ext.android.inject

class TrackDetailFragment : Fragment(), TrackDetailPresenter.View {

    private val presenter by inject<TrackDetailPresenter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.track_detail, container, false).also {
            val args = TrackDetailFragmentArgs.fromBundle(requireArguments())
            presenter.trackId = args.trackId
            presenter.bind(this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unbind()
    }

    override fun showTrackInfo(trackInfo: TrackInfo) {

    }
}