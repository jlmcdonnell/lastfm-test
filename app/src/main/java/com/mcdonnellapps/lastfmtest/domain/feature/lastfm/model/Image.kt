package com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model

data class Image(
    val text: String,
    val size: Size
) {
    enum class Size {
        Small, Medium, Large, ExtraLarge
    }
}