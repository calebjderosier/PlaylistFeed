package com.calebderosier.playlistfeed.ui.activities.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calebderosier.playlistfeed.data.api.PlaylistRetriever
import com.calebderosier.playlistfeed.data.models.Playlist
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class MainViewModel: ViewModel(), CoroutineScope {

    private val viewModelJob = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob


    val playlist: MutableLiveData<Playlist> by lazy {
        MutableLiveData<Playlist>()
    }

    private lateinit var isLoading: MutableLiveData<Boolean>
    private lateinit var showError: MutableLiveData<Boolean>

    init {
        initializeProperties()
        getPlaylistFromAPI()
    }

    /*
    * Set initial values of isLoading and showError live booleans
     */
    private fun initializeProperties() {
        getIsLoading()
        shouldShowError()
    }

    /*
    * Uses Retrofit coroutine to retrieve a playlist from the Deezer API, then updates corresponding fields
     */
    private fun getPlaylistFromAPI() {
        launch {
            try {
                isLoading.value = true
                playlist.setValue(PlaylistRetriever.retrievePlaylist())
            } catch (e: Exception) {
                showError.value = true
                e.printStackTrace()
            } finally {
                isLoading.value = false
            }

        }
    }

    /*
    * Returns a live Boolean indicating whether the data is currently loading
     */
    open fun getIsLoading(): LiveData<Boolean> {
        if (!::isLoading.isInitialized) {
            isLoading = MutableLiveData()
            isLoading.value = false
        }
        return isLoading
    }

    /*
    * Returns a live Boolean indicating whether to show an error
     */
    open fun shouldShowError(): LiveData<Boolean> {
        if (!::showError.isInitialized) {
            showError = MutableLiveData()
            showError.value = false
        }
        return showError
    }
}