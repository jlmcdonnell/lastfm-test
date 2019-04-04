package com.mcdonnellapps.lastfmtest.presenter.base

import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.mcdonnellapps.lastfmtest.common.AppExecutors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

open class BasePresenter<View : BaseView>(
    executors: AppExecutors
) : ViewModel(), LifecycleObserver {

    private val job = Job()
    protected val uiScope = CoroutineScope(executors.ui + job)
    protected val ioScope = CoroutineScope(executors.io + job)
    protected var view: View? = null

    @CallSuper
    open fun bind(view: View) {
        this.view = view
        view.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unbind() {
        job.cancel()
        view?.lifecycle?.removeObserver(this)
        view = null
    }

}