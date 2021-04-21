package com.calebderosier.playlistfeed.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calebderosier.playlistfeed.data.models.Song
import javax.inject.Inject

class DetailsViewModel @Inject constructor(): ViewModel() {

    var song: Song? = null
        private set

    private val mutableRequestActivityClose = MutableLiveData<Boolean>()
    val requestActivityClose: LiveData<Boolean> = mutableRequestActivityClose

    /**
    * Initializes the song object within DetailsViewModel using the given [song] object
     */
    fun init(song: Song) {
        this.song = song
    }

    /**
    * Handles the back button click event from the activity_details layout
     */
    fun onBackButtonClicked() {
        mutableRequestActivityClose.value = true
    }

    /**
     * Returns a string representing the given [lengthInSecs] in mins and secs with format XXmXXs
     */
    fun Int.secsToMinsAndSecsStr(lengthInSecs: Int): String {
        if (lengthInSecs > 0) return "${lengthInSecs / 60}m${lengthInSecs % 60}s"
        return "0m0s"
    }
}