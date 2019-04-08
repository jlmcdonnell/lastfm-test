package com.mcdonnellapps.lastfmtest.presentation.home

import com.mcdonnellapps.lastfmtest.domain.feature.common.preferences.interactor.AddRecentQuery
import com.mcdonnellapps.lastfmtest.domain.feature.common.preferences.interactor.RecentQueries
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmRepository
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
    private lateinit var searchPresenter: SearchPresenter
    private lateinit var view: SearchPresenter.View

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        lastFmRepository = mockk()
        getRecentQueries = mockk()
        addRecentQuery = mockk(relaxUnitFun = true)

        searchPresenter = SearchPresenter(
            appExecutors,
            lastFmRepository,
            getRecentQueries,
            addRecentQuery
        )

        view = mockk(relaxUnitFun = true)
        coEvery { view.lifecycle } returns createTestLifecycle()
    }

    @Test
    fun `when searching, clear search text`() {
        coEvery {
            lastFmRepository.searchMusic(any())
        } returns mockk()

        searchPresenter.bind(view)
        searchPresenter.query("1234")

        verify {
            view.clearSearchText()
        }
    }

    @Test
    fun `when searching, show loading`() {
        coEvery {
            lastFmRepository.searchMusic(any())
        } returns mockk()

        searchPresenter.bind(view)
        searchPresenter.query("1234")

        verify {
            view.showLoading()
        }
    }

    @Test
    fun `after searching, hide loading`() {
        coEvery {
            lastFmRepository.searchMusic(any())
        } returns mockk()

        searchPresenter.bind(view)
        searchPresenter.query("1234")

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

        searchPresenter.bind(view)
        searchPresenter.query("1234")

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

        searchPresenter.bind(view)
        searchPresenter.query("1234")

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

        searchPresenter.bind(view)
        searchPresenter.query("1234")

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

        searchPresenter.bind(view)
        searchPresenter.query("1234")

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

        searchPresenter.bind(view)
        searchPresenter.query("1234")

        verify {
            view.hideLoading()
        }
    }

    @Test
    fun `on search query error, show generic error`() {
        coEvery {
            lastFmRepository.searchMusic(any())
        } throws Exception()

        searchPresenter.bind(view)
        searchPresenter.query("1234")

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

        searchPresenter.bind(view)
        searchPresenter.query("1234")

        verify {
            view.hidePlaceholder()
        }
    }

    @Test
    fun `on empty search result, show empty state`() {
        coEvery {
            lastFmRepository.searchMusic(any())
        } returns MusicSearch()

        searchPresenter.bind(view)
        searchPresenter.query("1234")

        verify {
            view.showNoResultsPlaceholder()
        }

        verify(exactly = 0) {
            view.showSearchResult(any())
        }
    }

    @Test
    fun `on subscribe, show empty placeholder`() {
        searchPresenter.bind(view)
        verify { view.showEmptyPlaceholder() }
    }

    @Test
    fun `on subscribe, retrieve recent queries`() {
        val recentQueries = listOf("query")

        every {
            getRecentQueries.execute()
        } returns recentQueries

        searchPresenter.bind(view)

        verify { view.setRecentQueries(recentQueries) }
    }

    @Test
    fun `on search with results, add to recent query`() {
        coEvery {
            lastFmRepository.searchMusic("1234")
        } returns MusicSearch(tracks = listOf(mockk()))

        searchPresenter.bind(view)
        searchPresenter.query("1234")

        verify { addRecentQuery.execute("1234") }
    }

    @Test
    fun `on search with no results, don't add to recent query`() {
        coEvery {
            lastFmRepository.searchMusic("1234")
        } returns MusicSearch()

        searchPresenter.bind(view)
        searchPresenter.query("1234")

        verify(exactly = 0) { addRecentQuery.execute("1234") }
    }
}