package com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model

data class TrackInfo(
    val mbid: String,
    val name: String,
    val artist: Artist,
    val album: Album,
    val url: String
) {
    data class Artist(
        val name: String,
        val mbid: String
    )

    data class Album(
        val mbid: String,
        val artist: String,
        val title: String,
        val image: ImageCollection
    )
}