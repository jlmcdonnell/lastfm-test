package com.mcdonnellapps.lastfmtest.data.feature.lastfm

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmRepository
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.api.LastFmApi
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class LastFmRepositoryImpl(private val lastFmApi: LastFmApi) : LastFmRepository {

    override suspend fun searchMusicAsync(query: String): Deferred<MusicSearch> {
        return GlobalScope.async {
            val tracks = lastFmApi.searchTracksAsync(query)
            MusicSearch(tracks)
        }
    }
}