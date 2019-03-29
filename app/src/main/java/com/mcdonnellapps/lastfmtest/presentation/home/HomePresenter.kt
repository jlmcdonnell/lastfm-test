package com.mcdonnellapps.lastfmtest.presentation.home

import com.mcdonnellapps.lastfmtest.common.AppExecutors
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmRepository
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch
import com.mcdonnellapps.lastfmtest.presenter.base.BasePresenter
import com.mcdonnellapps.lastfmtest.presenter.base.BaseView
import kotlinx.coroutines.launch
import timber.log.Timber

class HomePresenter(
    executors: AppExecutors,
    private val repository: LastFmRepository
) : BasePresenter<HomePresenter.View>(executors) {

    fun query(query: String) = scope.launch {
        view?.clearSearchText()

        try {
            repository.searchMusicAsync(query).await().also {
                view?.showSearchResult(it)
            }
        } catch (e: Exception) {
            Timber.e(e, "Error searching for music")
            view?.showGenericError()
        }
    }

    interface View : BaseView {
        fun showSearchResult(searchResult: MusicSearch)
        fun clearSearchText()
    }
}