package com.mcdonnellapps.lastfmtest.data.feature.lastfm

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.api.LastFmApi
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Artist
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Track
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@Suppress("EXPERIMENTAL_API_USAGE")
class LastFmRepositoryImplTest {

    @MockK
    private lateinit var api: LastFmApi

    @InjectMockKs
    private lateinit var repository: LastFmRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `on music search, retrieve tracks`() = runBlocking {
        val tracks = listOf(mockk<Track>())
        val artists = emptyList<Artist>()

        every {
            api.searchTracks(any())
        } returns tracks

        every {
            api.searchArtists(any())
        } returns artists

        val actual = repository.searchMusic("artist")

        val expected = MusicSearch(
            tracks = tracks,
            artists = artists
        )

        assertEquals(expected, actual)
    }

    @Test
    fun `on music search, retrieve artists`() = runBlocking {
        val tracks = emptyList<Track>()
        val artists = listOf(mockk<Artist>())

        every {
            api.searchArtists(any())
        } returns artists

        every {
            api.searchTracks(any())
        } returns tracks

        val actual = repository.searchMusic("artist")

        val expected = MusicSearch(
            tracks = tracks,
            artists = artists
        )

        assertEquals(expected, actual)
    }

}