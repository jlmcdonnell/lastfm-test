package com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model

data class Album(
    val mbid: String,
    val name: String,
    val url: String,
    val artist: String,
    val image: ImageCollection
)