package com.mcdonnellapps.lastfmtest.ui.search

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mcdonnellapps.lastfmtest.R
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Album
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Artist
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Track
import com.mcdonnellapps.lastfmtest.presentation.search.SearchPresenter
import com.mcdonnellapps.lastfmtest.ui.track.detail.TrackDetailFragmentArgs
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.search.*
import org.koin.android.ext.android.inject

class SearchFragment : Fragment(R.layout.search), SearchPresenter.View {

    private val presenter by inject<SearchPresenter>()

    private lateinit var groupAdapter: GroupAdapter<ViewHolder>
    private lateinit var musicSection: Section
    private lateinit var tracksSection: Section
    private lateinit var artistsSection: Section
    private lateinit var albumSection: Section

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchResults()
        setupSearchText()
        presenter.bind(this)
    }

    override fun showSearchResult(searchResult: MusicSearch) {
        tracksSection.update(searchResult.tracks.map(::TrackItem))
        artistsSection.update(searchResult.artists.map(::ArtistItem))
        albumSection.update(searchResult.albums.map(::AlbumItem))

        musicSection.update(listOf(tracksSection, artistsSection, albumSection))
        results.visibility = View.VISIBLE
    }

    override fun clearSearchText() {
        searchText.setText("")
    }

    override fun clearSearchResult() {
        musicSection.update(emptyList())
        tracksSection.update(emptyList())
        artistsSection.update(emptyList())
    }

    override fun showLoading() {
        progress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress.visibility = View.GONE
    }

    override fun showEmptyPlaceholder() {
        musicSection.setPlaceholder(EmptyState(getString(R.string.search_empty_message)))
    }

    override fun showNoResultsPlaceholder() {
        musicSection.setPlaceholder(EmptyState(getString(R.string.search_empty_no_results)))
    }

    override fun hidePlaceholder() {
        musicSection.removePlaceholder()
    }

    override fun setRecentQueries(queries: List<String>) {
        ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, queries)
            .also(searchText::setAdapter)
    }

    override fun showTrackDetail(track: Track) {
        val args = TrackDetailFragmentArgs(trackId = track.mbid)

        findNavController().navigate(R.id.action_searchFragment_to_trackDetailFragment, args.toBundle())
    }

    override fun showAlbumDetail(album: Album) {
    }

    override fun showArtistDetail(artist: Artist) {
    }

    override fun showGenericError() {
        Toast.makeText(requireContext(), R.string.generic_error, Toast.LENGTH_SHORT).show()
    }

    private fun setupSearchText() {
        searchText.setOnEditorActionListener { _, _, _ ->
            submitQuery()
            return@setOnEditorActionListener true
        }

        searchText.setOnItemClickListener { _, _, _, _ ->
            submitQuery()
        }
    }

    private fun setupSearchResults() {
        musicSection = Section()

        tracksSection = Section().also {
            it.setHeader(HeaderItem(getString(R.string.search_header_tracks)))
        }
        artistsSection = Section().also {
            it.setHeader(HeaderItem(getString(R.string.search_header_artists)))
        }
        albumSection = Section().also {
            it.setHeader(HeaderItem(getString(R.string.search_header_albums)))
        }

        groupAdapter = GroupAdapter<ViewHolder>().also { it.add(musicSection) }
        groupAdapter.setOnItemClickListener { item, _ ->
            when (item) {
                is TrackItem -> presenter.trackClicked(item.track)
                is AlbumItem -> presenter.albumClicked(item.album)
                is ArtistItem -> presenter.artistClicked(item.artist)
            }
        }

        results.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = groupAdapter
            itemAnimator = null
        }
    }

    private fun submitQuery() {
        presenter.query(searchText.text!!.toString())

        (requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(requireActivity().window.decorView.windowToken, 0)
    }
}