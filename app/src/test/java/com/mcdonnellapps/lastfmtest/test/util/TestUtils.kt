package com.mcdonnellapps.lastfmtest.test.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import com.mcdonnellapps.lastfmtest.common.AppExecutors
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers

fun createTestLifecycle(event: Lifecycle.Event = Lifecycle.Event.ON_START): Lifecycle {
    return LifecycleRegistry(mockk()).also {
        it.handleLifecycleEvent(event)
    }
}

fun testAppExecutors() = AppExecutors(
    io = Dispatchers.Unconfined,
    ui = Dispatchers.Unconfined
)