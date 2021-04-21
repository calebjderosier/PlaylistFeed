package com.calebderosier.playlistfeed.ui.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.calebderosier.playlistfeed.PlaylistFeedApp
import com.calebderosier.playlistfeed.R
import com.calebderosier.playlistfeed.data.models.Song
import com.calebderosier.playlistfeed.databinding.ActivityDetailsBinding
import com.calebderosier.playlistfeed.ui.viewmodels.DetailsViewModel
import com.calebderosier.playlistfeed.ui.viewmodels.ViewModelFactory
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: DetailsViewModel
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // bind data to layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)
        binding.viewModel = viewModel

        // check whether song data has been passed into Activity
        val songJson: String? = intent?.getStringExtra("songJson")
        if (songJson != null) {
            val song = Gson().fromJson(songJson, Song::class.java)
            viewModel.init(song)
            updateUI(song)
        }

        // listener for back button in layout
        viewModel.requestActivityClose.observe(this, {
            finish()
        })

    }

    /**
    * Updates UI with data passed in with the given [song] object
     */
    private fun updateUI(song: Song) {
        Picasso.get().load(song.album?.coverLarge).into(iv_cover)
        if (song.isExplicit) iv_explicit_icon.setVisibility(View.INVISIBLE)
//        tv_song_length.text = Int.parseLength(song.duration.toInt())
    }

}