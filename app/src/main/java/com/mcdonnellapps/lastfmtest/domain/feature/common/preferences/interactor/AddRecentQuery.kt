package com.mcdonnellapps.lastfmtest.domain.feature.common.preferences.interactor

import com.mcdonnellapps.lastfmtest.domain.feature.common.preferences.AppPreferences
import com.mcdonnellapps.lastfmtest.domain.feature.common.preferences.mapper.SearchQueryMapper
import com.mcdonnellapps.lastfmtest.domain.feature.common.preferences.model.SearchQuery

class AddRecentQuery(private val appPreferences: AppPreferences) {

    companion object {
        private const val HISTORY_COUNT = 100
    }

    fun execute(query: String) {
        val newQuery = SearchQuery(
            query,
            System.currentTimeMillis()
        )

        appPreferences.recentQueries
            .asSequence()
            .map(SearchQueryMapper::map)
            .filter { it.query != newQuery.query }
            .plus(newQuery)
            .take(HISTORY_COUNT)
            .map { SearchQueryMapper.reverseMap(it) }
            .also {
                appPreferences.recentQueries = it.toList()
            }
    }

}