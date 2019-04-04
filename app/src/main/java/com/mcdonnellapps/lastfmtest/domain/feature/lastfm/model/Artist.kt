package com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model

data class Artist(
    val mbid: String,
    val name: String,
    val url: String,
    val image: ImageCollection,
    val listeners: Int
)