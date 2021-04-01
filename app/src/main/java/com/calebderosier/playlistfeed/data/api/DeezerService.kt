package com.calebderosier.playlistfeed.data.api

import com.calebderosier.playlistfeed.data.models.Playlist
import retrofit2.http.GET

/*
* Interface providing function which calls the Deezer API
 */
interface DeezerService {
    @GET("/playlist/1976454162")
    suspend fun getPlaylistFromDeezer(): Playlist
}