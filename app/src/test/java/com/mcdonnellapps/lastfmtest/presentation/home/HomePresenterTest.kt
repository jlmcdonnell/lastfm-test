package com.mcdonnellapps.lastfmtest.presentation.home

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmRepository
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Track
import com.mcdonnellapps.lastfmtest.test.util.createTestLifecycle
import com.mcdonnellapps.lastfmtest.test.util.testAppExecutors
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomePresenterTest {


    private val appExecutors = testAppExecutors()
    private lateinit var lastFmRepository: LastFmRepository
    private lateinit var homePresenter: HomePresenter
    private lateinit var view: HomePresenter.View

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        lastFmRepository = mockk()
        homePresenter = HomePresenter(appExecutors, lastFmRepository)

        view = mockk(relaxUnitFun = true)
        every { view.lifecycle } returns createTestLifecycle()
    }

    @Test
    fun `when searching, clear search text`() {
        every {
            lastFmRepository.searchMusic(any())
        } returns mockk()

        homePresenter.bind(view)
        homePresenter.query("1234")

        verify {
            view.clearSearchText()
        }
    }

    @Test
    fun `when searching, show loading`() {
        every {
            lastFmRepository.searchMusic(any())
        } returns mockk()

        homePresenter.bind(view)
        homePresenter.query("1234")

        verify {
            view.showLoading()
        }
    }

    @Test
    fun `after searching, hide loading`() {
        every {
            lastFmRepository.searchMusic(any())
        } returns mockk()

        homePresenter.bind(view)
        homePresenter.query("1234")

        verify {
            view.hideLoading()
        }
    }

    @Test
    fun `on search query, show search result`() = runBlocking {
        val tracks = listOf(mockk<Track>())
        val result = MusicSearch(tracks)

        every {
            lastFmRepository.searchMusic(any())
        } returns result

        homePresenter.bind(view)
        homePresenter.query("1234")

        verify {
            view.showSearchResult(result)
        }
    }

    @Test
    fun `on search query, clear search text`() = runBlocking {
        val result = mockk<MusicSearch>()

        every {
            lastFmRepository.searchMusic(any())
        } returns result

        homePresenter.bind(view)
        homePresenter.query("1234")

        verify {
            view.clearSearchText()
        }
    }

    @Test
    fun `on search query, clear search result`() = runBlocking {
        val result = mockk<MusicSearch>()

        every {
            lastFmRepository.searchMusic(any())
        } returns result

        homePresenter.bind(view)
        homePresenter.query("1234")

        verify {
            view.clearSearchText()
        }
    }

    @Test
    fun `on search query, show loading`() = runBlocking {
        val result = mockk<MusicSearch>()

        every {
            lastFmRepository.searchMusic(any())
        } returns result

        homePresenter.bind(view)
        homePresenter.query("1234")

        verify {
            view.showLoading()
        }
    }

    @Test
    fun `on search result, hide loading`() = runBlocking {
        val result = mockk<MusicSearch>()

        every {
            lastFmRepository.searchMusic(any())
        } returns result

        homePresenter.bind(view)
        homePresenter.query("1234")

        verify {
            view.hideLoading()
        }
    }

    @Test
    fun `on search query error, show generic error`() {
        every {
            lastFmRepository.searchMusic(any())
        } throws Exception()

        homePresenter.bind(view)
        homePresenter.query("1234")

        verify {
            view.showGenericError()
        }
    }

    @Test
    fun `on search result, hide empty state`() {
        val result = MusicSearch(listOf(mockk()))

        every {
            lastFmRepository.searchMusic(any())
        } returns result

        homePresenter.bind(view)
        homePresenter.query("1234")

        verify {
            view.hidePlaceholder()
        }
    }

    @Test
    fun `on empty search result, show empty state`() {
        every {
            lastFmRepository.searchMusic(any())
        } returns MusicSearch(emptyList())

        homePresenter.bind(view)
        homePresenter.query("1234")

        verify {
            view.showEmptyPlaceholder()
        }

        verify(exactly = 0) {
            view.showSearchResult(any())
        }
    }
}