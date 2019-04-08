package com.mcdonnellapps.lastfmtest.domain.feature.common.preferences.interactor

import com.mcdonnellapps.lastfmtest.domain.feature.common.preferences.AppPreferences

class RecentQueries(private val appPreferences: AppPreferences) {

    fun execute(): List<String> {
        return appPreferences.recentQueries
    }

}