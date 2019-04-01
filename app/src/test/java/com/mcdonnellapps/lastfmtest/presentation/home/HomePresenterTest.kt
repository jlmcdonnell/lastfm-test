package com.mcdonnellapps.lastfmtest.presentation.home

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmRepository
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.MusicSearch
import com.mcdonnellapps.lastfmtest.test.util.createTestLifecycle
import com.mcdonnellapps.lastfmtest.test.util.testAppExecutors
import io.mockk.*
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
        coEvery {
            lastFmRepository.searchMusicAsync(any())
        } returns mockk()

        homePresenter.subscribe(view)
        homePresenter.query("1234")

        coVerify {
            view.clearSearchText()
        }
    }

    @Test
    fun `when searching, show loading`() {
        coEvery {
            lastFmRepository.searchMusicAsync(any())
        } returns mockk()

        homePresenter.subscribe(view)
        homePresenter.query("1234")

        coVerify {
            view.showLoading()
        }
    }

    @Test
    fun `after searching, hide loading`() {
        coEvery {
            lastFmRepository.searchMusicAsync(any())
        } returns mockk()

        homePresenter.subscribe(view)
        homePresenter.query("1234")

        coVerify {
            view.hideLoading()
        }
    }

    @Test
    fun `on search query, show search result`() = runBlocking {
        val result = mockk<MusicSearch>()

        coEvery {
            lastFmRepository.searchMusicAsync(any())
        } returns result

        homePresenter.subscribe(view)
        homePresenter.query("1234")

        coVerify {
            view.showSearchResult(result)
        }
    }

    @Test
    fun `on search query error, show generic error`() {
        coEvery {
            lastFmRepository.searchMusicAsync(any())
        } throws Exception()

        homePresenter.subscribe(view)
        homePresenter.query("1234")

        verify {
            view.showGenericError()
        }
    }
}