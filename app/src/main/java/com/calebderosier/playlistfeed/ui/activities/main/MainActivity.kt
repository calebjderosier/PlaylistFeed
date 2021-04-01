package com.calebderosier.playlistfeed.ui.activities.main

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.calebderosier.playlistfeed.R
import com.calebderosier.playlistfeed.data.models.Playlist
import com.calebderosier.playlistfeed.ui.adapters.SongListAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_song_list.layoutManager = LinearLayoutManager(this)

        getPlaylistAndUpdateUI()
    }

    /*
    * Gets playlist from ViewModel and updates Main Activity UI accordingly
    * If network is not connected, offers Try Again option
     */
    private fun getPlaylistAndUpdateUI() {
        if (isNetworkConnected()) {
            generateViewModel()
            viewModel.playlist.observe(this, Observer<Playlist>{ playlist ->
                // set playlist header artwork
                Picasso.get().load(playlist.picture_big).into(iv_toolbar_bg)
                rv_song_list.scheduleLayoutAnimation()
                // connect song list data to UI
                rv_song_list.adapter = SongListAdapter(playlist)
            })
        } else {
            AlertDialog.Builder(this).setTitle("No Internet Connection")
                .setMessage("Unable to pull API data. Please check your connection and try again.")
                .setPositiveButton("Retry", DialogInterface.OnClickListener(function = retry))
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
    }

    /*
    * Generates ViewModel from MainViewModelFactory
     */
    private fun generateViewModel() {
        val factory = MainViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    /*
    * Dialog option to retry pulling API data after reconnecting to the Internet
     */
    val retry = { _: DialogInterface, _: Int ->
        getPlaylistAndUpdateUI()
    }

    /*
    * Returns boolean indicating whether the device is connected to the Internet
     */
    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}