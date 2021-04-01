package com.calebderosier.playlistfeed.ui.activities.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.calebderosier.playlistfeed.R
import com.calebderosier.playlistfeed.data.models.Song
import com.calebderosier.playlistfeed.databinding.ActivityDetailsBinding
import com.google.gson.Gson
import com.google.gson.JsonElement
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

        val songJson: String? = intent?.getStringExtra("songJson") ?: null
        if (songJson != null) {
            val song = Gson().fromJson(songJson, Song::class.java)
            viewModel.init(song)
            updateUI(song)
        }

        viewModel.requestActivityClose.observe(this, {
            finish()
        })

    }

    /*
    * Updates UI with data passed in with the given [song] object
     */
    private fun updateUI(song: Song) {
        Picasso.get().load(song.album?.cover_big).into(iv_cover)
        if (song.explicit_lyrics) iv_explicit_icon.setVisibility(View.INVISIBLE)
        tv_song_length.text = parseLength(song.duration.toInt())
    }

    /*
    * Returns a string representing the given [lengthInSecs] in mins and secs with format XXmXXs
     */
    fun parseLength(lengthInSecs: Int): String {
        if (lengthInSecs > 0) return "${lengthInSecs / 60}m${lengthInSecs % 60}s"
        return "0m0s"
    }
}