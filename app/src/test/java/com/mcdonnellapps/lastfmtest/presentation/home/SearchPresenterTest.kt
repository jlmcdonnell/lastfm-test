package com.mcdonnellapps.lastfmtest.presentation.home

import com.mcdonnellapps.lastfmtest.domain.feature.common.preferences.interactor.AddRecentQuery
import com.mcdonnellapps.lastfmtest.domain.feature.common.preferences.interactor.RecentQueries
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmRepository
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Album
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Artist
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Track
import com.mcdonnellapps.lastfmtest.presentation.search.SearchPresenter
import com.mcdonnellapps.lastfmtest.test.util.createTestLifecycle
import com.mcdonnellapps.lastfmtest.test.util.testAppExecutors
import io.mockk.coEvery
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
class SearchPresenterTest {


    private val appExecutors = testAppExecutors()
    private lateinit var lastFmRepository: LastFmRepository
    private lateinit var addRecentQuery: AddRecentQuery
    private lateinit var getRecentQueries: RecentQueries
    private lateinit var presenter: SearchPresenter
    private lateinit var view: SearchPresenter.View

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        lastFmRepository = mockk()
        getRecentQueries = mockk()
        addRecentQuery = mockk(relaxUnitFun = true)

        presenter = SearchPresenter(
            appExecutors,
            lastFmRepository,
            getRecentQueries,
            addRecentQuery
        )

        view = mockk(relaxUnitFun = true)
        every { view.lifecycle } returns createTestLifecycle()
    }

    @Test
    fun `when searching, clear search text`() {
        coEvery {
            lastFmRepository.searchMusic(any())
        } returns mockk()

        presenter.bind(view)
        presenter.query("1234")

        verify {
            view.clearSearchText()
        }
    }

    @Test
    fun `when searching, show loading`() {
        coEvery {
            lastFmRepository.searchMusic(any())
        } returns mockk()

        presenter.bind(view)
        presenter.query("1234")

        verify {
            view.showLoading()
        }
    }

    @Test
    fun `after searching, hide loading`() {
        coEvery {
            lastFmRepository.searchMusic(any())
        } returns mockk()

        presenter.bind(view)
        presenter.query("1234")

        verify {
            view.hideLoading()
        }
    }

    @Test
    fun `on search query, show search result`() = runBlocking {
        val tracks = listOf(mockk<Track>())
        val result = MusicSearch(tracks = tracks)

        coEvery {
            lastFmRepository.searchMusic(any())
        } returns result

        presenter.bind(view)
        presenter.query("1234")

        verify {
            view.showSearchResult(result)
        }
    }

    @Test
    fun `on search query, clear search text`() = runBlocking {
        val result = mockk<MusicSearch>()

        coEvery {
            lastFmRepository.searchMusic(any())
        } returns result

        presenter.bind(view)
        presenter.query("1234")

        verify {
            view.clearSearchText()
        }
    }

    @Test
    fun `on search query, clear search result`() = runBlocking {
        val result = mockk<MusicSearch>()

        coEvery {
            lastFmRepository.searchMusic(any())
        } returns result

        presenter.bind(view)
        presenter.query("1234")

        verify {
            view.clearSearchText()
        }
    }

    @Test
    fun `on search query, show loading`() = runBlocking {
        val result = mockk<MusicSearch>()

        coEvery {
            lastFmRepository.searchMusic(any())
        } returns result

        presenter.bind(view)
        presenter.query("1234")

        verify {
            view.showLoading()
        }
    }

    @Test
    fun `on search result, hide loading`() = runBlocking {
        val result = mockk<MusicSearch>()

        coEvery {
            lastFmRepository.searchMusic(any())
        } returns result

        presenter.bind(view)
        presenter.query("1234")

        verify {
            view.hideLoading()
        }
    }

    @Test
    fun `on search query error, show generic error`() {
        coEvery {
            lastFmRepository.searchMusic(any())
        } throws Exception()

        presenter.bind(view)
        presenter.query("1234")

        verify {
            view.showGenericError()
        }
    }

    @Test
    fun `on search result, hide empty state`() {
        val result = MusicSearch(tracks = listOf(mockk()))

        coEvery {
            lastFmRepository.searchMusic(any())
        } returns result

        presenter.bind(view)
        presenter.query("1234")

        verify {
            view.hidePlaceholder()
        }
    }

    @Test
    fun `on empty search result, show empty state`() {
        coEvery {
            lastFmRepository.searchMusic(any())
        } returns MusicSearch()

        presenter.bind(view)
        presenter.query("1234")

        verify {
            view.showNoResultsPlaceholder()
        }

        verify(exactly = 0) {
            view.showSearchResult(any())
        }
    }

    @Test
    fun `on bind, show empty placeholder`() {
        presenter.bind(view)
        verify { view.showEmptyPlaceholder() }
    }

    @Test
    fun `on bind, retrieve recent queries`() {
        val recentQueries = listOf("query")

        every {
            getRecentQueries.execute()
        } returns recentQueries

        presenter.bind(view)

        verify { view.setRecentQueries(recentQueries) }
    }

    @Test
    fun `on search with results, add to recent query`() {
        coEvery {
            lastFmRepository.searchMusic("1234")
        } returns MusicSearch(tracks = listOf(mockk()))

        presenter.bind(view)
        presenter.query("1234")

        verify { addRecentQuery.execute("1234") }
    }

    @Test
    fun `on search with no results, don't add to recent query`() {
        coEvery {
            lastFmRepository.searchMusic("1234")
        } returns MusicSearch()

        presenter.bind(view)
        presenter.query("1234")

        verify(exactly = 0) { addRecentQuery.execute("1234") }
    }

    @Test
    fun `on track clicked, show track detail`() {
        val track = mockk<Track>()

        presenter.bind(view)
        presenter.trackClicked(track)

        verify { view.showTrackDetail(track) }
    }

    @Test
    fun `on artist clicked, show artist detail`() {
        val artist = mockk<Artist>()

        presenter.bind(view)
        presenter.artistClicked(artist)

        verify { view.showArtistDetail(artist) }
    }

    @Test
    fun `on album clicked, show album detail`() {
        val album = mockk<Album>()

        presenter.bind(view)
        presenter.albumClicked(album)

        verify { view.showAlbumDetail(album) }
    }

    @Test
    fun `on re-bind, if result exists, show result`() {
        val track = mockk<Track>()
        val result = MusicSearch(listOf(track))

        coEvery { lastFmRepository.searchMusic("1234") } returns result

        presenter.bind(view)
        presenter.query("1234")
        presenter.unbind()

        verify(exactly = 1) { view.showSearchResult(result) }

        presenter.bind(view)
        verify(exactly = 2) { view.showSearchResult(result) }
    }
}