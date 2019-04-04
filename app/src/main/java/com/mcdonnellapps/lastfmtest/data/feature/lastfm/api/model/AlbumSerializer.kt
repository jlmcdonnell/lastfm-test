package com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.model

data class AlbumSerializer(
    val mbid: String,
    val name: String,
    val url: String,
    val image: List<ImageSerializer>,
    val artist: String
)