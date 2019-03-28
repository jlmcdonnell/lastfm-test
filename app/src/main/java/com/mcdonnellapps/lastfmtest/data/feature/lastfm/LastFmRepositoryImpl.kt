package com.mcdonnellapps.lastfmtest.data.feature.lastfm

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmApi
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmRepository
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Result
import kotlinx.coroutines.Deferred

class LastFmRepositoryImpl(private val lastFmApi: LastFmApi) : LastFmRepository {

    override suspend fun searchAsync(query: String): Deferred<List<Result>> {
        return lastFmApi.searchAsync(query)
    }
}