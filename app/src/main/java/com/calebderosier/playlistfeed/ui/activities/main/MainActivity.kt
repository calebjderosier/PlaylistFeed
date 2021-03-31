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
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.calebderosier.playlistfeed.R
import com.calebderosier.playlistfeed.data.models.Playlist
import com.calebderosier.playlistfeed.data.models.Song
import com.calebderosier.playlistfeed.ui.activities.details.DetailsActivity
import com.calebderosier.playlistfeed.ui.adapters.SongListAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = MainViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        rv_song_list.layoutManager = LinearLayoutManager(this)

        if (isNetworkConnected()) {
            getPlaylistAndUpdateUI()
        } else {
            AlertDialog.Builder(this).setTitle("No Internet Connection")
                .setMessage("Unable to pull API data. Please check your connection and try again.")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
    }

    /*
    * Gets playlist from ViewModel and updates Main Activity UI accordingly
     */
    private fun getPlaylistAndUpdateUI() {
        viewModel.playlist.observe(this, Observer<Playlist>{ playlist ->
            // set playlist name and description
            tv_playlist_title.text = playlist?.title
            tv_playlist_desc.text = playlist?.description

            // connect song list data to UI
            rv_song_list.adapter = SongListAdapter(playlist)
        })
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
    *
    * @param view:
     */
    fun openSongDetails(view: View) {
        // TODO: make song titles and artist not simply the first one in layout
        val title = findViewById<TextView>(R.id.tv_title).text
        val artist = findViewById<TextView>(R.id.tv_artist).text
        val songData: Song? = viewModel.playlist.value?.tracks?.data?.first { item -> (item.title === title && item.artist.name === artist)}

        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("songJson", Gson().toJson(songData))
        startActivity(intent)
    }

}