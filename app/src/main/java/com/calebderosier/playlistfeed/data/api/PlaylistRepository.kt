package com.calebderosier.playlistfeed.data.api

import com.calebderosier.playlistfeed.data.models.Playlist
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

/**
*
 */
class PlaylistRepository @Inject constructor(private val deezerService: DeezerService){
    /**
    *
    */
    suspend fun retrievePlaylist(): Playlist = deezerService.getPlaylistFromDeezer()
}