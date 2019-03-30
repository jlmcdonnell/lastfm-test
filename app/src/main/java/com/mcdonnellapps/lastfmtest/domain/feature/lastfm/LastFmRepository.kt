package com.mcdonnellapps.lastfmtest.domain.feature.lastfm

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch

interface LastFmRepository {
    suspend fun searchMusicAsync(query: String): MusicSearch
}