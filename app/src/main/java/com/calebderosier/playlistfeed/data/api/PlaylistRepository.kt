package com.calebderosier.playlistfeed.data.api

import com.calebderosier.playlistfeed.data.models.Playlist
import javax.inject.Inject

/**
*
 */
class PlaylistRepository @Inject constructor(private val deezerService: DeezerService){
    /**
    * Pulls playlist from Deezer API using DeezerService
    */
    suspend fun retrievePlaylist(): Playlist = deezerService.getPlaylistFromDeezer()
}