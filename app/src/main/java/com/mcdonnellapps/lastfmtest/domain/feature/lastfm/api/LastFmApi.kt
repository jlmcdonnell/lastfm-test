package com.mcdonnellapps.lastfmtest.domain.feature.lastfm.api

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Track

interface LastFmApi {

    fun searchTracks(query: String): List<Track>

}