package com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.mapper

import com.mcdonnellapps.lastfmtest.common.Mapper
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.model.TrackInfoSerializer
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.TrackInfo

object TrackInfoMapper : Mapper<TrackInfoSerializer, TrackInfo>() {

    override fun map(input: TrackInfoSerializer): TrackInfo {
        return TrackInfo(
            mbid = input.mbid,
            artist = input.artist.let { artist ->
                TrackInfo.Artist(
                    name = artist.name,
                    mbid = artist.mbid
                )
            },
            album = input.album.let { album ->
                TrackInfo.Album(
                    mbid = album.mbid,
                    artist = album.artist,
                    image = ImageCollectionMapper.map(album.image),
                    title = album.title
                )
            },
            url = input.url,
            name = input.name
        )
    }
}