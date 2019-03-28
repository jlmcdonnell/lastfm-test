package com.mcdonnellapps.lastfmtest.common.extensions.lastfm.model

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Track

fun Track.defaultImageUrl() = image.extraLarge.text