package com.mcdonnellapps.lastfmtest.data.feature.lastfm.api

import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.interceptor.ApiKeyInterceptor
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.interceptor.FormatAsJsonInterceptor
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.mapper.AlbumSearchMapper
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.mapper.ArtistSearchMapper
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.mapper.TrackInfoMapper
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.mapper.TrackSearchMapper
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.model.AlbumSearchSerializer
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.model.ArtistSearchSerializer
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.model.TrackInfoSerializer
import com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.model.TrackSearchSerializer
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.LastFMException
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.api.LastFmApi
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Album
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Artist
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.Track
import com.mcdonnellapps.lastfmtest.domain.feature.lastfm.model.TrackInfo
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class LastFmApiImpl(
    apiUrl: String,
    apiKeyInterceptor: ApiKeyInterceptor
) : LastFmApi {

    interface Service {
        @GET("/2.0?method=album.search")
        fun searchAlbums(@Query("album") album: String): Call<AlbumSearchSerializer>

        @GET("/2.0?method=artist.search")
        fun searchArtists(@Query("artist") artist: String): Call<ArtistSearchSerializer>

        @GET("/2.0?method=track.search")
        fun searchTracks(@Query("track") track: String): Call<TrackSearchSerializer>

        @GET("/2.0?method=track.getInfo")
        fun trackById(@Query("mbid") mbid: String): Call<TrackInfoSerializer>
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(FormatAsJsonInterceptor)
        .addInterceptor(apiKeyInterceptor)
        .build()

    private val service = Retrofit.Builder()
        .baseUrl(apiUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Service::class.java)

    override fun searchTracks(query: String): List<Track> {
        return executeCall(service.searchTracks(query)) {
            TrackSearchMapper.map(it)
        }!!
    }

    override fun searchArtists(query: String): List<Artist> {
        return executeCall(service.searchArtists(query)) {
            ArtistSearchMapper.map(it)
        }!!
    }

    override fun searchAlbums(query: String): List<Album> {
        return executeCall(service.searchAlbums(query)) {
            AlbumSearchMapper.map(it)
        }!!
    }

    override fun trackById(mbid: String): TrackInfo {
        return executeCall(service.trackById(mbid)) {
            TrackInfoMapper.map(it)
        }!!
    }

    private fun <T, V> executeCall(call: Call<T>, mapper: (LastFmApiImpl.(T) -> V)?): V? {
        return call.execute().let {
            if (it.isSuccessful) {
                mapper?.invoke(this, it.body()!!)
            } else {
                val error = LastFMException.Error.HTTPError(
                    code = it.code(),
                    message = it.message()
                )

                throw LastFMException(error)
            }
        }
    }
}