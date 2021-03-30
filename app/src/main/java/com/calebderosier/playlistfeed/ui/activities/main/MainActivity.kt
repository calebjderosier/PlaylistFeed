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
import com.calebderosier.playlistfeed.ui.activities.details.DetailsActivity
import com.calebderosier.playlistfeed.ui.adapters.SongListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songList.layoutManager = LinearLayoutManager(this)

        if (isNetworkConnected()) {
            retrievePlaylist()
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
    private fun retrievePlaylist() {
        val mainActivityJob = Job()

        val errorHandler = CoroutineExceptionHandler { _, exception ->
            AlertDialog.Builder(this).setTitle("Error")
                .setMessage(exception.message)
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }

        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)
        coroutineScope.launch(errorHandler) {
            val result = getPlaylist()

            // set playlist name and description
            playlistTitle.text = result?.title
            playlistDesc.text = result?.description

            // connect song list data to UI
            songList.adapter = SongListAdapter(result)
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
        // TODO: get other playlist data from result here, pass into intent

        val title = findViewById<TextView>(R.id.songTitle)
        val artist = findViewById<TextView>(R.id.songArtist)
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("title", title.text)
        intent.putExtra("artist", artist.text)
        startActivity(intent)
    }

}