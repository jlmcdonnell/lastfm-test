package com.mcdonnellapps.lastfmtest.domain.feature.lastfm.interactor

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.SearchHistory

class GetRecentQueries(private val searchHistory: SearchHistory) {

    suspend fun execute(): List<String> {
        return searchHistory.recentQueries()
    }

}