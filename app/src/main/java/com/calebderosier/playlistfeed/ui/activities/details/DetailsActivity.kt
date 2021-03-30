package com.calebderosier.playlistfeed.ui.activities.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.calebderosier.playlistfeed.R
import com.calebderosier.playlistfeed.api.PlaylistRetriever

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val title = intent.getStringExtra("title")
        val artist = intent.getStringExtra("artist")
    }

    suspend fun getDetails(title: String, artist: String) {
        val result = PlaylistRetriever.getPlaylist()
    }
}