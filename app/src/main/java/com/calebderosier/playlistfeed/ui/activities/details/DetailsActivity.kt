package com.calebderosier.playlistfeed.ui.activities.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.calebderosier.playlistfeed.R
import com.calebderosier.playlistfeed.data.models.Song
import com.calebderosier.playlistfeed.databinding.ActivityDetailsBinding
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: DetailsViewModel
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        val factory = DetailsViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)
        binding.viewModel = viewModel

        val song = Gson().fromJson(intent.getStringExtra("songJson"), Song::class.java)
        viewModel.init(song)

        viewModel.requestActivityClose.observe(this, Observer {
            finish()
        })

        updateUI(song)
    }

    /*
    * Updates UI with data passed in with the given [song] object
     */
    fun updateUI(song: Song) {
        Picasso.get().load(song.album.cover_big).into(iv_cover)
        if (song.explicit_lyrics == false) iv_explicit_icon.setVisibility(View.INVISIBLE)
        tv_song_length.text = parseLength(song.duration.toInt())
    }

    /*
    * Returns a string representing the given [lengthInSecs] in mins and secs with format XXmXXs
     */
    private fun parseLength(lengthInSecs: Int) = "${lengthInSecs / 60}m${lengthInSecs % 60}s"
}