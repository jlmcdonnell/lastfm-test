package com.mcdonnellapps.lastfmtest.domain.feature.lastfm

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch

interface LastFmRepository {
    suspend fun searchMusic(query: String): MusicSearch
}