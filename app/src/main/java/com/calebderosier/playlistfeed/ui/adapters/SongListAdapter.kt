package com.calebderosier.playlistfeed.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.calebderosier.playlistfeed.R
import com.calebderosier.playlistfeed.data.models.Playlist
import com.calebderosier.playlistfeed.data.models.Song
import com.calebderosier.playlistfeed.ui.views.DetailsActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.song_item.view.*

class SongListAdapter(private val playlist: Playlist?) : RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val songView = LayoutInflater.from(parent.context).inflate(R.layout.song_item, parent, false)
        return SongViewHolder(songView)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        if (playlist?.tracks?.data == null) return
        holder.songView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("songJson", Gson().toJson(playlist.tracks.data[position]))
            context.startActivity(intent)
        }
        holder.bindPlaylist(playlist.tracks.data[position])
    }

    override fun getItemCount(): Int = (playlist?.numberOfTracks ?: 0)

    class SongViewHolder(val songView: View) : RecyclerView.ViewHolder(songView) {

        fun bindPlaylist(song: Song?) {
            Picasso.get().load(song?.album?.coverSmall).into(songView.iv_song_artwork)

            songView.tv_song_title.text = song?.title ?: ""
            songView.tv_song_artist.text = song?.artist?.name ?: ""
        }
    }
}