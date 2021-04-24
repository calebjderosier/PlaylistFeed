package com.calebderosier.playlistfeed.ui.views

import android.os.Bundle
import android.os.Parcel
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.calebderosier.playlistfeed.data.models.Song
import com.calebderosier.playlistfeed.data.models.SongDetails
import com.calebderosier.playlistfeed.databinding.ActivityDetailsBinding
import com.calebderosier.playlistfeed.ui.viewmodels.DetailsViewModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: DetailsViewModel

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // bind data to layout
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailsViewModel::class.java)
        binding.viewModel = viewModel

        // check whether song data has been passed into Activity
        val songDetails: SongDetails? = intent.getParcelableExtra("songDetails")
        if (songDetails != null) viewModel.init(songDetails)

        // listener for back button in layout
        viewModel.requestActivityClose.observe(this, {
            finish()
        })

    }
}