package com.calebderosier.playlistfeed.data.api

import com.calebderosier.playlistfeed.data.models.Playlist
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
* Singleton from which DeezerService is called
 */
object PlaylistRetriever {
    private val service: DeezerService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.deezer.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(DeezerService::class.java)
    }

    /*
    * Public function that calls the Deezer API using an instance of DeezerService
     */
    suspend fun retrievePlaylist(): Playlist = service.getPlaylistFromDeezer()
}