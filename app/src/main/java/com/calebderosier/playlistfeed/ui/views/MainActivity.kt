package com.calebderosier.playlistfeed.ui.views

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.calebderosier.playlistfeed.PlaylistFeedApp
import com.calebderosier.playlistfeed.R
import com.calebderosier.playlistfeed.data.api.PlaylistRepository
import com.calebderosier.playlistfeed.ui.viewmodels.MainViewModel
import com.calebderosier.playlistfeed.ui.adapters.SongListAdapter
import com.calebderosier.playlistfeed.ui.viewmodels.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var playlistRepository: PlaylistRepository

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        (application as PlaylistFeedApp).playlistFeedComponent.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        rv_song_list.layoutManager = LinearLayoutManager(this)

        getPlaylistAndUpdateUI()
    }

    /**
    * Gets playlist from ViewModel and updates Main Activity UI accordingly
    * If network is not connected, offers Try Again option
     */
    private fun getPlaylistAndUpdateUI() {
        if (isNetworkConnected()) {
            viewModel.playlist.observe(this, { playlist ->
                // set playlist header artwork
                Picasso.get().load(playlist?.pictureLarge).into(iv_toolbar_bg)
                rv_song_list.scheduleLayoutAnimation()
                // connect song list data to UI
                rv_song_list.adapter = SongListAdapter(playlist)
            })
        } else {
            AlertDialog.Builder(this).setTitle(getString(R.string.noConnection))
                .setMessage(getString(R.string.cannotPullData))
                .setPositiveButton(getString(R.string.retry), DialogInterface.OnClickListener(function = retry))
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }
    }

    /**
    * Dialog option to retry pulling API data after reconnecting to the Internet
     */
    private val retry = { _: DialogInterface, _: Int ->
        getPlaylistAndUpdateUI()
    }

    /**
    * Returns boolean indicating whether the device is connected to the Internet
     */
    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

}