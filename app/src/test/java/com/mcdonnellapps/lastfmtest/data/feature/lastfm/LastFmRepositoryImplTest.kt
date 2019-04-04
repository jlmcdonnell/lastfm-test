package com.mcdonnellapps.lastfmtest.data.feature.lastfm

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.api.LastFmApi
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

        every {
            api.searchTracks(any())
        } returns tracks

        val actual = repository.searchMusic("track")

        val expected = MusicSearch(
            tracks = tracks
        )

        assertEquals(expected, actual)
    }

}