package com.calebderosier.playlistfeed.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calebderosier.playlistfeed.data.api.PlaylistRepository
import com.calebderosier.playlistfeed.data.models.Playlist
import kotlinx.coroutines.*
import javax.inject.Inject

open class MainViewModel @Inject constructor(private val playlistRepository: PlaylistRepository): ViewModel() {

    private val mutablePlaylist: MutableLiveData<Playlist> by lazy {
        MutableLiveData<Playlist>()
    }
    val playlist: LiveData<Playlist> = mutablePlaylist.asLiveData()

    private val mutableIsLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = mutableIsLoading.asLiveData()

    private val mutableShowError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean> = mutableShowError.asLiveData()

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

    /**
     * Extension function to return a LiveData object from MutableLiveData
     */
    private fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>
}