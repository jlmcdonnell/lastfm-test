package com.mcdonnellapps.lastfmtest.domain.feature.preferences

interface SearchHistory {
    suspend fun recentQueries(): List<String>
    suspend fun addQuery(query: String)
}