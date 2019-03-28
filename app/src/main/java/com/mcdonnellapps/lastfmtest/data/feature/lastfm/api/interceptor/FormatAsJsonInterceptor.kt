package com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.interceptor

import okhttp3.Interceptor
import okhttp3.Response

object FormatAsJsonInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val url = request.url().newBuilder()
            .addQueryParameter("format", "json")
            .build()

        return request.newBuilder()
            .url(url)
            .build()
            .let(chain::proceed)
    }
}