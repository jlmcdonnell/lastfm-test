package com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model

data class Track(
    val mbid: String,
    val name: String,
    val url: String,
    val image: List<Image>
)