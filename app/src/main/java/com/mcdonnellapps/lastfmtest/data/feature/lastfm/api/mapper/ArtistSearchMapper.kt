package com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.mapper

import com.mcdonnellapps.lastfmtest.common.Mapper
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.model.ArtistSearchSerializer
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Artist

object ArtistSearchMapper : Mapper<ArtistSearchSerializer, List<Artist>>() {

    override fun map(input: ArtistSearchSerializer): List<Artist> {
        return input.results.artistmatches.artist.map(ArtistMapper::map)
    }
}