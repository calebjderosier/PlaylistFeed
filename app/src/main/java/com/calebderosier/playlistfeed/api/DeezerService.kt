package com.calebderosier.playlistfeed.api

import com.calebderosier.playlistfeed.data.Playlist
import retrofit2.http.GET

interface DeezerService {
    @GET("/playlist/908622995")
    suspend fun retrievePlaylist(): Playlist
}