package com.mcdonnellapps.lastfmtest.domain.feature.lastfm

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch

interface LastFmRepository {
    fun searchMusic(query: String): MusicSearch
}