package com.mcdonnellapps.lastfmtest.data.feature.lastfm

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmRepository
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.api.LastFmApi
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch

class LastFmRepositoryImpl(private val lastFmApi: LastFmApi) : LastFmRepository {

    override fun searchMusic(query: String): MusicSearch {
        val tracks = lastFmApi.searchTracks(query)
        return MusicSearch(tracks)
    }
}