package com.mcdonnellapps.lastfmtest.data.feature.lastfm

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmApi
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Result
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class LastFmApiImpl(private val apiKey: String) : LastFmApi {
    override suspend fun searchAsync(query: String): Deferred<List<Result>> {
        return GlobalScope.async {
            listOf(Result(query))
        }
    }
}