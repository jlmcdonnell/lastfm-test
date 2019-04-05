package com.mcdonnellapps.lastfmtest.common.extensions.lastfm.model

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch

fun MusicSearch.isEmpty() = tracks.isEmpty() && artists.isEmpty() && albums.isEmpty()