package com.mcdonnellapps.lastfmtest.ui.search

import androidx.fragment.app.Fragment
import com.mcdonnellapps.lastfmtest.R
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch
import com.mcdonnellapps.lastfmtest.presentation.search.SearchPresenter

class SearchFragment : Fragment(R.layout.search), SearchPresenter.View {


    override fun showSearchResult(searchResult: MusicSearch) {
    }

    override fun clearSearchResult() {
    }

    override fun clearSearchText() {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showEmptyPlaceholder() {
    }

    override fun showNoResultsPlaceholder() {
    }

    override fun hidePlaceholder() {
    }

    override fun setRecentQueries(queries: List<String>) {
    }
}