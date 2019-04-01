package com.mcdonnellapps.lastfmtest.presentation.home

import com.mcdonnellapps.lastfmtest.common.AppExecutors
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmRepository
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch
import com.mcdonnellapps.lastfmtest.presenter.base.BasePresenter
import com.mcdonnellapps.lastfmtest.presenter.base.BaseView
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class HomePresenter(
    private val executors: AppExecutors,
    private val repository: LastFmRepository
) : BasePresenter<HomePresenter.View>(executors) {

    fun query(query: String) = scope.launch {
        if (query.isEmpty()) {
            return@launch
        }

        view?.clearSearchText()
        view?.clearSearchResult()
        view?.showLoading()

        try {
            val result = withContext(executors.io) {
                repository.searchMusicAsync(query)
            }

            view?.hideLoading()
            view?.showSearchResult(result)

            if (result.tracks.isEmpty()) {
                view?.showEmpty()
            } else {
                view?.hideEmpty()
            }
        } catch (e: Exception) {
            Timber.e(e, "Error searching for music")
            view?.showGenericError()
            view?.hideLoading()
        }
    }

    interface View : BaseView {
        fun showSearchResult(searchResult: MusicSearch)
        fun clearSearchResult()
        fun clearSearchText()
        fun showLoading()
        fun hideLoading()
        fun showEmpty()
        fun hideEmpty()
    }
}