package com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.model

import com.google.gson.annotations.SerializedName

data class ImageSerializer(
    @SerializedName("#text")
    val text: String,
    val size: String
)