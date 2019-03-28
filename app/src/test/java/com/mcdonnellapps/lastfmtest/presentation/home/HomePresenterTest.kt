package com.mcdonnellapps.lastfmtest.presentation.home

import com.mcdonnellapps.lastfmtest.common.AppExecutors
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFmRepository
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Result
import com.mcdonnellapps.lastfmtest.test.util.createTestLifecycle
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomePresenterTest {


    private var appExecutors = AppExecutors()
    private lateinit var lastFmRepository: LastFmRepository
    private lateinit var homePresenter: HomePresenter
    private lateinit var view: HomePresenter.View

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        appExecutors = AppExecutors()
        lastFmRepository = mockk()
        homePresenter = HomePresenter(appExecutors, lastFmRepository)

        view = mockk(relaxUnitFun = true)
        every { view.lifecycle } returns createTestLifecycle()
    }

    @Test
    fun `on search query, show results`() {
        val result = Result("1234")

        coEvery {
            lastFmRepository.searchAsync(any())
        } returns GlobalScope.async { listOf(Result("1234")) }

        homePresenter.subscribe(view)
        homePresenter.query("1234")

        coVerify {
            view.showResults(listOf(result))
        }
    }

    @Test
    fun `on search query error, show generic error`() {
        coEvery {
            lastFmRepository.searchAsync(any())
        } returns GlobalScope.async { throw Exception() }

        homePresenter.subscribe(view)
        homePresenter.query("1234")

        coVerify {
            view.showGenericError()
        }
    }
}