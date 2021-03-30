package com.calebderosier.playlistfeed.api

import com.calebderosier.playlistfeed.data.Playlist
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

    suspend fun getPlaylist(): Playlist = service.retrievePlaylist()
}