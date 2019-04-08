package com.mcdonnellapps.lastfmtest.domain.feature.common.preferences.interactor

import com.mcdonnellapps.lastfmtest.domain.feature.common.preferences.AppPreferences
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class RecentQueriesTest {

    private lateinit var appPreferences: AppPreferences
    private lateinit var recentQueries: RecentQueries

    @Before
    fun setUp() {
        appPreferences = mockk(relaxUnitFun = true)
        recentQueries = RecentQueries(appPreferences)
    }

    @Test
    fun `execute returns list of queries`() {
        every { appPreferences.recentQueries } returns listOf("1234")

        assertEquals(recentQueries.execute(), listOf("1234"))
    }

}