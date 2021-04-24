package com.calebderosier.playlistfeed.ui.viewmodels

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calebderosier.playlistfeed.data.models.SongDetails
import com.calebderosier.playlistfeed.util.asLiveData
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DetailsViewModel @Inject constructor(): ViewModel() {

    var song: SongDetails? = null
        private set

    private val mutableRequestActivityClose = MutableLiveData<Boolean>()
    val requestActivityClose = mutableRequestActivityClose.asLiveData()

    /**
    * Initializes the song object within DetailsViewModel using the given [song] object
     */
    fun init(song: SongDetails) {
        this.song = song
    }

    /**
    * Handles the back button click event from the activity_details layout
     */
    fun onBackButtonClicked() {
        mutableRequestActivityClose.value = true
    }

    companion object {
        /**
         * Uses Picasso to load given [imageUrl] into given [imageView]
         * Located within companion object because @BindingAdapter methods must be static
         */
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImageFromUri(imageView: ImageView, imageUrl: String) {
            if (!imageUrl.isNullOrBlank()) Picasso.get().load(imageUrl).into(imageView)
        }
    }
}