package com.mcdonnellapps.lastfmtest.domain.feature.lastfm.interactor

import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.SearchHistory

class AddRecentQuery(private val searchHistory: SearchHistory) {

    suspend fun execute(query: String) {
        return searchHistory.addQuery(query)
    }

}