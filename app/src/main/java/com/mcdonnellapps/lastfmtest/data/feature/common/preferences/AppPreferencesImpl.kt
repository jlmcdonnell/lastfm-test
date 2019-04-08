package com.mcdonnellapps.lastfmtest.data.feature.common.preferences

import android.content.Context
import com.mcdonnellapps.lastfmtest.domain.feature.common.preferences.AppPreferences

class AppPreferencesImpl(context: Context) : AppPreferences {

    companion object {
        private const val PREFERENCES_NAME = "prefs"
        private const val KEY_RECENT_QUERIES = "recent_queries"
    }

    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override var recentQueries: List<String>
        get() {
            return preferences.getStringSet(KEY_RECENT_QUERIES, mutableSetOf())!!.toList()
        }
        set(value) {
            preferences.edit()
                .putStringSet(KEY_RECENT_QUERIES, value.toSet())
                .apply()
        }

}