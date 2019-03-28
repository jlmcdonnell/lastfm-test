package com.mcdonnellapps.lastfmtest.presenter.base

import androidx.lifecycle.LifecycleOwner

interface BaseView : LifecycleOwner {
    fun showGenericError() = Unit
}