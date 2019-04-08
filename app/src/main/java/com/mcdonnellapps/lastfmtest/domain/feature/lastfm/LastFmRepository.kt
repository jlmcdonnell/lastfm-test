package com.mcdonnellapps.lastfmtest.domain.feature.lastfm

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.TrackInfo

interface LastFmRepository {
    suspend fun searchMusic(query: String): MusicSearch
    suspend fun trackById(mbid: String): TrackInfo
}