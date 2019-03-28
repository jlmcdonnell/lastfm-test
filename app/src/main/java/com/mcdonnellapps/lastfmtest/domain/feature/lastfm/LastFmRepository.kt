package com.mcdonnellapps.lastfmtest.domain.feature.lastfm

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Result
import kotlinx.coroutines.Deferred

interface LastFmRepository {
    suspend fun searchAsync(query: String): Deferred<List<Result>>
}