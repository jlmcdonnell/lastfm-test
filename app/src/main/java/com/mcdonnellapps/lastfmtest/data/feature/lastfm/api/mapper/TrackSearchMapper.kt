package com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.mapper

import com.mcdonnellapps.lastfmtest.common.Mapper
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.model.TrackSearchSerializer
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Track

object TrackSearchMapper : Mapper<TrackSearchSerializer, List<Track>>() {

    override fun map(input: TrackSearchSerializer): List<Track> {
        return input.results.trackmatches.track.map(TrackMapper::map)
    }
}