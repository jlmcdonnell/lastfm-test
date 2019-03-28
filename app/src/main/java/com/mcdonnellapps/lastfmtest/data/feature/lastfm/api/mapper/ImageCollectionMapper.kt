package com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.mapper

import com.mcdonnellapps.lastfmtest.common.Mapper
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.model.ImageSerializer
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Image
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.ImageCollection

object ImageCollectionMapper : Mapper<List<ImageSerializer>, ImageCollection>() {

    override fun map(input: List<ImageSerializer>): ImageCollection {
        val small = input.first { it.size == "small" }.let(this::mapImage)
        val medium = input.first { it.size == "medium" }.let(this::mapImage)
        val large = input.first { it.size == "large" }.let(this::mapImage)
        val extraLarge = input.first { it.size == "extralarge" }.let(this::mapImage)

        return ImageCollection(small, medium, large, extraLarge)
    }

    private fun mapImage(serializer: ImageSerializer): Image {
        return Image(
            text = serializer.text,
            size = when (serializer.size) {
                "small" -> Image.Size.Small
                "medium" -> Image.Size.Medium
                "large" -> Image.Size.Large
                "extralarge" -> Image.Size.ExtraLarge
                else -> Image.Size.Small
            }
        )
    }
}