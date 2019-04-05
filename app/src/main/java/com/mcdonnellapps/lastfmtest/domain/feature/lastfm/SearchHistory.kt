package com.mcdonnellapps.lastfmtest.domain.feature.lastfm

interface SearchHistory {
    suspend fun recentQueries(): List<String>
    suspend fun addQuery(query: String)
}