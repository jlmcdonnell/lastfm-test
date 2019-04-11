package com.mcdonnellapps.lastfmtest.ui.track.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mcdonnellapps.lastfmtest.R
import com.mcdonnellapps.lastfmtest.common.extensions.lastfm.model.defaultImageUrl
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.TrackInfo
import kotlinx.android.synthetic.main.track_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrackDetailFragment : Fragment(R.layout.track_detail), TrackDetailPresenter.View {

    private val presenter by viewModel<TrackDetailPresenter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = TrackDetailFragmentArgs.fromBundle(requireArguments())
        presenter.trackId = args.trackId
        presenter.bind(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unbind()
    }

    override fun showTrackInfo(trackInfo: TrackInfo) {
        Glide.with(requireView())
            .load(trackInfo.defaultImageUrl())
            .into(image)

        name.text = trackInfo.name
        trackInfo.wiki?.let {
            wiki.text = it.content
        }
    }

    override fun showGenericError() {
        super.showGenericError()
        Toast.makeText(requireContext(), R.string.generic_error, Toast.LENGTH_SHORT).show()
    }
}