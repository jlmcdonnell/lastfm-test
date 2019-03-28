package com.mcdonnellapps.lastfmtest.test.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import io.mockk.mockk

fun createTestLifecycle(event: Lifecycle.Event = Lifecycle.Event.ON_START): Lifecycle {
    return LifecycleRegistry(mockk()).also {
        it.handleLifecycleEvent(event)
    }
}