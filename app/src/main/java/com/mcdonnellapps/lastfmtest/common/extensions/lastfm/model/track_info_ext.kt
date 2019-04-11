package com.mcdonnellapps.lastfmtest.common.extensions.lastfm.model

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.TrackInfo

fun TrackInfo.defaultImageUrl() = album.image.extraLarge.text