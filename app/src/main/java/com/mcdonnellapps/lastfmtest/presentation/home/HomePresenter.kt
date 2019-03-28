package com.mcdonnellapps.lastfmtest.presentation.home

import com.mcdonnellapps.lastfmtest.common.AppExecutors
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmRepository
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Result
import com.mcdonnellapps.lastfmtest.presenter.base.BasePresenter
import com.mcdonnellapps.lastfmtest.presenter.base.BaseView
import kotlinx.coroutines.launch

class HomePresenter(
    executors: AppExecutors,
    private val repository: LastFmRepository
) : BasePresenter<HomePresenter.View>(executors) {

    fun query(query: String) = scope.launch {
        try {
            repository.searchAsync(query).await().also {
                view?.showResults(it)
            }
        } catch (e: Exception) {
            view?.showGenericError()
        }
    }

    interface View : BaseView {
        fun showResults(results: List<Result>)
    }
}