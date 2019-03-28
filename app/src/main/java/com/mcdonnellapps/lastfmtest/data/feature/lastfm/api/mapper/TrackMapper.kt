package com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.mapper

import com.mcdonnellapps.lastfmtest.common.Mapper
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.model.TrackSerializer
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Track

object TrackMapper : Mapper<TrackSerializer, Track>() {

    override fun map(input: TrackSerializer): Track {
        return Track(
            mbid = input.mbid,
            image = input.image.map(ImageMapper::map),
            name = input.name,
            url = input.url
        )
    }
}