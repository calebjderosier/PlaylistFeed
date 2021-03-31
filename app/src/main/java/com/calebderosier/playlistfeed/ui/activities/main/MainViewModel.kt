package com.calebderosier.playlistfeed.ui.activities.main

import android.R
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calebderosier.playlistfeed.data.api.PlaylistRetriever
import com.calebderosier.playlistfeed.data.models.Playlist
import kotlinx.coroutines.*

class MainViewModel: ViewModel() {

    val playlist: MutableLiveData<Playlist> by lazy {
        MutableLiveData<Playlist>()
    }

    init {
        getPlaylistFromAPI()
    }

    /*
    * Uses Retrofit coroutine to retrieve a playlist from the Deezer API, then updates corresponding fields
     */
    private fun getPlaylistFromAPI() {
        val mainActivityJob = Job()

        val errorHandler = CoroutineExceptionHandler { _, exception ->
            Log.d("Error", exception.message ?: "")
        }

        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
        coroutineScope.launch(errorHandler) {
            playlist.setValue(PlaylistRetriever.retrievePlaylist())
        }
    }
}