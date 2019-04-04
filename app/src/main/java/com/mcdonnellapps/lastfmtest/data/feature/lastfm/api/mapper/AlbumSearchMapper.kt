package com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.mapper

import com.mcdonnellapps.lastfmtest.common.Mapper
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.model.AlbumSearchSerializer
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Album

object AlbumSearchMapper : Mapper<AlbumSearchSerializer, List<Album>>() {

    override fun map(input: AlbumSearchSerializer): List<Album> {
        return input.results.albummatches.album.map(AlbumMapper::map)
    }
}