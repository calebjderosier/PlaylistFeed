package com.calebderosier.playlistfeed.ui.activities.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.calebderosier.playlistfeed.R
import com.calebderosier.playlistfeed.data.models.Song
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.song_item.view.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val song = Gson().fromJson(intent.getStringExtra("songJson"), Song::class.java)

        Picasso.get().load(song?.album?.cover_big).into(coverArt)
        songTitle.text = song?.title ?: ""
        songArtist.text = song?.artist?.name ?: ""
        songAlbum.text = song?.album?.title ?: ""
        if (song.duration!="") songLength.text = parseLength(song.duration.toInt())
    }

    /*
    * Converts integer length in seconds to string displaying minutes and seconds
    *
    * @param lengthInSecs: integer representing the length of the track in seconds
     */
    private fun parseLength(lengthInSecs: Int) = "${lengthInSecs / 60}m${lengthInSecs % 60}s"
}