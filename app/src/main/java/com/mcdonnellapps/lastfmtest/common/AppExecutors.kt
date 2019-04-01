package com.mcdonnellapps.lastfmtest.common

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class AppExecutors(
    val io: CoroutineContext = Dispatchers.Default,
    val ui: CoroutineContext = Dispatchers.Main
)