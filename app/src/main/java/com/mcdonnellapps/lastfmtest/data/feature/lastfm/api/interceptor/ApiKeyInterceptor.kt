package com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url().newBuilder()
            .addQueryParameter("api_key", apiKey)
            .build()

        return request.newBuilder()
            .url(url)
            .build()
            .let(chain::proceed)
    }
}