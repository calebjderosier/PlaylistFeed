package com.calebderosier.playlistfeed.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calebderosier.playlistfeed.data.api.PlaylistRepository
import com.calebderosier.playlistfeed.data.models.Playlist
import com.calebderosier.playlistfeed.util.asLiveData
import kotlinx.coroutines.*
import javax.inject.Inject

open class MainViewModel @Inject constructor(private val playlistRepository: PlaylistRepository): ViewModel() {

    private val mutablePlaylist: MutableLiveData<Playlist> by lazy {
        MutableLiveData<Playlist>()
    }
    val playlist = mutablePlaylist.asLiveData()

    private val mutableIsLoading = MutableLiveData<Boolean>()
    val isLoading = mutableIsLoading.asLiveData()

    private val mutableShowError = MutableLiveData<Boolean>()
    val showError = mutableShowError.asLiveData()

    init {
        getPlaylistFromAPI()
    }

    /**
    * Uses Retrofit coroutine to retrieve a playlist from the Deezer API, then updates corresponding fields
     */
    private fun getPlaylistFromAPI() {
        viewModelScope.launch {
            try {
                mutableIsLoading.value = true
                mutablePlaylist.setValue(playlistRepository.retrievePlaylist())
            } catch (e: Exception) {
                mutableShowError.value = true
                e.printStackTrace()
            } finally {
                mutableIsLoading.value = false
            }

        }
    }
}