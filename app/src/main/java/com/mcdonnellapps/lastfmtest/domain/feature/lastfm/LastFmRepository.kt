package com.mcdonnellapps.lastfmtest.domain.feature.lastfm

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch
import kotlinx.coroutines.Deferred

interface LastFmRepository {
    suspend fun searchMusicAsync(query: String): Deferred<MusicSearch>
}