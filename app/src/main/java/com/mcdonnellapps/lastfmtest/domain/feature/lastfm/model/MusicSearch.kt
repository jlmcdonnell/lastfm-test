package com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model

data class MusicSearch(
    val tracks: List<Track> = emptyList(),
    val artists: List<Artist> = emptyList(),
    val albums: List<Album> = emptyList()
)