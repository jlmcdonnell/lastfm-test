package com.mcdonnellapps.lastfmtest.data.feature.lastfm

import android.content.Context
import com.mcdonnellapps.lastfmtest.domain.feature.common.preferences.SearchHistory

class SearchHistoryImpl(context: Context) :
    SearchHistory {

    companion object {
        private const val PREFERENCES_NAME = "search_history"
        private const val KEY_RECENT_QUERIES = "recent_queries"
        private const val HISTORY_COUNT = 100
    }

    private data class Query(val query: String, val date: Long)

    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override suspend fun recentQueries(): List<String> {
        return preferences.getStringSet(KEY_RECENT_QUERIES, mutableSetOf())!!
            .map { mapToQuery(it).query }
            .sorted()
    }

    override suspend fun addQuery(query: String) {
        val newQuery = Query(query, System.currentTimeMillis())

        preferences.getStringSet(KEY_RECENT_QUERIES, emptySet())!!
            .asSequence()
            .map(this::mapToQuery)
            .filter { it.query != newQuery.query }
            .plus(newQuery)
            .take(HISTORY_COUNT)
            .map { mapQueryToString(it) }
            .toSet()
            .also {
                preferences.edit()
                    .putStringSet(KEY_RECENT_QUERIES, it)
                    .apply()
            }
    }

    private fun mapToQuery(query: String): Query {
        return Query(
            query = query.substring(0, query.lastIndexOf(":")),
            date = query.substring(query.lastIndexOf(":") + 1, query.length).toLong()
        )
    }

    private fun mapQueryToString(query: Query): String {
        return "${query.query}:${query.date}"
    }
}