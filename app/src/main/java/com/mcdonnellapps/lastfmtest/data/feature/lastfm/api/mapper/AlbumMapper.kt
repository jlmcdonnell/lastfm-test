package com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.mapper

import com.mcdonnellapps.lastfmtest.common.Mapper
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.model.AlbumSerializer
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Album

object AlbumMapper : Mapper<AlbumSerializer, Album>() {

    override fun map(input: AlbumSerializer): Album {
        return Album(
            mbid = input.mbid,
            image = ImageCollectionMapper.map(input.image),
            name = input.name,
            artist = input.artist,
            url = input.url
        )
    }
}