package com.mcdonnellapps.lastfmtest.ui.track.detail

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFMException
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmRepository
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.TrackInfo
import com.mcdonnellapps.lastfmtest.test.util.createTestLifecycle
import com.mcdonnellapps.lastfmtest.test.util.testAppExecutors
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class TrackDetailPresenterTest {

    private lateinit var view: TrackDetailPresenter.View
    private lateinit var repository: LastFmRepository
    private lateinit var presenter: TrackDetailPresenter

    @Before
    fun setUp() {
        repository = mockk()
        view = mockk(relaxUnitFun = true)
        presenter = TrackDetailPresenter(testAppExecutors(), repository)
        every { view.lifecycle } returns createTestLifecycle()
    }

    @Test
    fun `on bind, show track info`() {
        val trackId = "1234"
        val trackInfo = mockk<TrackInfo>()

        coEvery {
            repository.trackById("1234")
        } returns trackInfo

        presenter.trackId = trackId
        presenter.bind(view)

        verify {
            view.showTrackInfo(trackInfo)
        }
    }

    @Test
    fun `on track not found, show generic error`() {
        coEvery {
            repository.trackById("1234")
        } throws LastFMException(LastFMException.Error.TrackNotFoundError)

        presenter.trackId = "1234"
        presenter.bind(view)

        verify {
            view.showGenericError()
        }
    }

}