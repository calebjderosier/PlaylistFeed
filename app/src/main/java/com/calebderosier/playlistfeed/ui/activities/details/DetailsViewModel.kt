package com.calebderosier.playlistfeed.ui.activities.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calebderosier.playlistfeed.data.models.Song

class DetailsViewModel: ViewModel() {

    var song: Song? = null

    val requestActivityClose: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    /*
    * Initializes the song object within DetailsViewModel using the given [song] object
     */
    fun init(song: Song) {
        this.song = song
    }

    /*
    * Handles the back button click event from the activity_details layout
     */
    fun onBackButtonClicked() {
        requestActivityClose.value = true
    }
}