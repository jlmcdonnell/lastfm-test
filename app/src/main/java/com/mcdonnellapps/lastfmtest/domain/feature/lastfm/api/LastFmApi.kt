package com.mcdonnellapps.lastfmtest.domain.feature.lastfm.api

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Track

interface LastFmApi {

    fun searchTracksAsync(query: String): List<Track>

}