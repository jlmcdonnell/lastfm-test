package com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.mapper

import com.mcdonnellapps.lastfmtest.common.Mapper
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.model.ImageSerializer
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Image

object ImageMapper : Mapper<ImageSerializer, Image>() {

    override fun map(input: ImageSerializer): Image {
        return Image(
            text = input.text,
            size = when (input.size) {
                "small" -> Image.Size.Small
                "medium" -> Image.Size.Medium
                "large" -> Image.Size.Large
                "extralarge" -> Image.Size.ExtraLarge
                else -> Image.Size.Small
            }
        )
    }
}