package com.mcdonnellapps.lastfmtest.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.mcdonnellapps.lastfmtest.R
import com.mcdonnellapps.lastfmtest.common.extensions.lastfm.model.defaultImageUrl
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Track
import com.mcdonnellapps.lastfmtest.presentation.home.HomePresenter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.search_header_item.*
import kotlinx.android.synthetic.main.track_search_item.*
import org.koin.android.ext.android.inject

class HomeActivity : AppCompatActivity(), HomePresenter.View {

    private val presenter by inject<HomePresenter>()

    private val groupAdapter = GroupAdapter<ViewHolder>()
    private val tracksSection = Section()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        presenter.subscribe(this)
        presenter.query("Time")


        groupAdapter.apply {
            add(HeaderItem(getString(R.string.home_header_tracks)))
            add(tracksSection)
        }

        results.layoutManager = LinearLayoutManager(this)
        results.adapter = groupAdapter

    }

    override fun showSearchResult(searchResult: MusicSearch) {
        tracksSection.update(searchResult.tracks.map(::TrackItem))
    }

    override fun showGenericError() {
        Toast.makeText(this, R.string.generic_error, Toast.LENGTH_SHORT).show()
    }

    class HeaderItem(private val title: String) : Item() {

        override fun getLayout() = R.layout.search_header_item

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.title.text = title
        }
    }

    class TrackItem(private val track: Track) : Item() {

        override fun getLayout() = R.layout.track_search_item

        override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.track.text = track.name
            viewHolder.artist.text = track.artist

            Glide.with(viewHolder.root)
                .load(track.defaultImageUrl())
                .into(viewHolder.image)
        }
    }
}
