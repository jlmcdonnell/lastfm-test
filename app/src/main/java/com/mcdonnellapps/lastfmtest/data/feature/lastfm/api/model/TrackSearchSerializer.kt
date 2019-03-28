package com.mcdonnellapps.lastfmtest.data.feature.lastfm.api.model

data class TrackSearchSerializer(val results: Results) {
    data class Results(val trackmatches: TrackMatches) {
        data class TrackMatches(val track: List<TrackSerializer>)
    }
}