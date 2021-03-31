package com.calebderosier.playlistfeed.ui.activities.main

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.calebderosier.playlistfeed.R
import com.calebderosier.playlistfeed.api.PlaylistRetriever.getPlaylist
import com.calebderosier.playlistfeed.data.api.PlaylistRetriever.retrievePlaylist
import com.calebderosier.playlistfeed.data.models.Playlist
import com.calebderosier.playlistfeed.data.models.Song
import com.calebderosier.playlistfeed.ui.activities.details.DetailsActivity
import com.calebderosier.playlistfeed.ui.adapters.SongListAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private var playlist: Playlist? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songList.layoutManager = LinearLayoutManager(this)

        if (isNetworkConnected()) {
            getPlaylistFromAPI()
        } else {
            AlertDialog.Builder(this).setTitle("No Internet Connection")
                .setMessage("Unable to pull API data. Please check your connection and try again.")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
    }

    /*
    * Uses Retrofit coroutine to retrieve a playlist from the Deezer API, then updates corresponding fields
     */
    private fun getPlaylistFromAPI() {
        val mainActivityJob = Job()

        val errorHandler = CoroutineExceptionHandler { _, exception ->
            AlertDialog.Builder(this).setTitle("Error")
                .setMessage(exception.message)
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }

        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
        coroutineScope.launch(errorHandler) {
            playlist = retrievePlaylist()

            // set playlist name and description
            playlistTitle.text = playlist?.title
            playlistDesc.text = playlist?.description

            // connect song list data to UI
            songList.adapter = SongListAdapter(playlist)
        }
    }

    /*
    * Helper function to check whether the device is connected to the Internet
     */
    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    /*
    * Creates an intent through which to open a Song Details screen
     */
    fun openSongDetails(view: View) {
        // TODO: make song titles and artist not simply the first one in layout
        val title = findViewById<TextView>(R.id.songTitle).text
        val artist = findViewById<TextView>(R.id.songArtist).text
        val songData: Song? = playlist?.tracks?.data?.first { item -> (item.title === title && item.artist.name === artist)}

        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("songJson", Gson().toJson(songData))
        startActivity(intent)
    }

}