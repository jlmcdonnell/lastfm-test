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
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch
import com.mcdonnellapps.lastfmtest.presentation.search.SearchPresenter
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.search.*
import org.koin.android.ext.android.inject

class SearchFragment : Fragment(R.layout.search), SearchPresenter.View {

    private val presenter by inject<SearchPresenter>()

    private val groupAdapter = GroupAdapter<ViewHolder>()
    private val musicSection = Section()
    private val tracksSection = Section()
    private val artistsSection = Section()
    private val albumSection = Section()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bind(this)

        tracksSection.setHeader(HeaderItem(getString(R.string.search_header_tracks)))
        artistsSection.setHeader(HeaderItem(getString(R.string.search_header_artists)))
        albumSection.setHeader(HeaderItem(getString(R.string.search_header_albums)))

        groupAdapter.add(musicSection)

        results.layoutManager = LinearLayoutManager(requireContext())
        results.adapter = groupAdapter

        setupSearchText()

        results.itemAnimator = null
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

    private fun submitQuery() {
        presenter.query(searchText.text!!.toString())

        (requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(requireActivity().window.decorView.windowToken, 0)
    }
}