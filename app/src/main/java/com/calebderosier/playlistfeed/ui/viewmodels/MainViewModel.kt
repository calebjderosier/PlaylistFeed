package com.calebderosier.playlistfeed.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calebderosier.playlistfeed.data.api.PlaylistRepository
import com.calebderosier.playlistfeed.data.models.Playlist
import kotlinx.coroutines.*
import javax.inject.Inject

open class MainViewModel @Inject constructor(private val playlistRepository: PlaylistRepository): ViewModel() {

    val playlist: MutableLiveData<Playlist> by lazy {
        MutableLiveData<Playlist>()
    }

    var isLoading = MutableLiveData<Boolean>()
        private set
    var showError = MutableLiveData<Boolean>()
        private set

    init {
        getPlaylistFromAPI()
    }

    /**
    * Uses Retrofit coroutine to retrieve a playlist from the Deezer API, then updates corresponding fields
     */
    private fun getPlaylistFromAPI() {
        viewModelScope.launch {
            try {
                isLoading.value = true
                playlist.setValue(playlistRepository.retrievePlaylist())
            } catch (e: Exception) {
                showError.value = true
                e.printStackTrace()
            } finally {
                isLoading.value = false
            }

        }
    }
}