package com.calebderosier.playlistfeed.data.api

import androidx.lifecycle.MutableLiveData
import com.calebderosier.playlistfeed.data.models.Playlist
import retrofit2.http.GET

interface DeezerService {
    @GET("/playlist/908622995")
    suspend fun getPlaylistFromDeezer(): Playlist
}