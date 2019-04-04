package com.mcdonnellapps.lastfmtest.ui.home

import com.bumptech.glide.Glide
import com.mcdonnellapps.lastfmtest.R
import com.mcdonnellapps.lastfmtest.common.extensions.lastfm.model.defaultImageUrl
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Album
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.album_search_item.*

class AlbumItem(private val album: Album) : Item() {

    override fun getLayout() = R.layout.album_search_item

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.album.text = album.name
        viewHolder.artist.text = album.artist

        Glide.with(viewHolder.root)
            .load(album.defaultImageUrl())
            .into(viewHolder.image)
    }

}