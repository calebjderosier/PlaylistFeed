package com.calebderosier.playlistfeed.ui.activities.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.calebderosier.playlistfeed.R
import com.calebderosier.playlistfeed.data.models.Song
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val factory = DetailsViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)

        val song = Gson().fromJson(intent.getStringExtra("songJson"), Song::class.java)
        updateUI(song)

//        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
//            // Handle the back button event
//        }
    }

    /*
    * Updates UI with data passed in via song object
    *
    * @param song: Song object containing data through which to update UI
     */
    fun updateUI(song: Song) {
        Picasso.get().load(song.album.cover_big).into(iv_cover)
        if (song.explicit_lyrics == false) iv_explicit_icon.setVisibility(View.INVISIBLE)
        tv_title.text = song.title
        tv_artist.text = song.artist.name
//        tv_album.text = song.album.title
        tv_song_length.text = parseLength(song.duration.toInt())
    }

    /*
    * Converts integer length in seconds to string displaying minutes and seconds
    *
    * @param lengthInSecs: integer representing the length of the track in seconds
     */
    private fun parseLength(lengthInSecs: Int) = "${lengthInSecs / 60}m${lengthInSecs % 60}s"
}