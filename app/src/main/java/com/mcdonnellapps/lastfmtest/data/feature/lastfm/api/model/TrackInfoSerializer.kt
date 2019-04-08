package com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.model

data class TrackInfoSerializer(
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
        val image: List<ImageSerializer>
    )
}