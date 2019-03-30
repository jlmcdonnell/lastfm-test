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
        view?.clearSearchText()
        view?.showLoading()

        try {
            val result = withContext(executors.io) {
                repository.searchMusicAsync(query)
            }

            view?.hideLoading()
            view?.showSearchResult(result)
        } catch (e: Exception) {
            Timber.e(e, "Error searching for music")
            view?.showGenericError()
        }
    }

    interface View : BaseView {
        fun showSearchResult(searchResult: MusicSearch)
        fun clearSearchText()
        fun showLoading()
        fun hideLoading()
    }
}