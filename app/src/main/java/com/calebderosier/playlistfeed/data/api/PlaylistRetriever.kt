package com.calebderosier.playlistfeed.data.api

import androidx.lifecycle.MutableLiveData
import com.calebderosier.playlistfeed.data.models.Playlist
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PlaylistRetriever {
    private val service: DeezerService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.deezer.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(DeezerService::class.java)
    }

    suspend fun retrievePlaylist(): Playlist = service.getPlaylistFromDeezer()
}