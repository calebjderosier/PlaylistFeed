package com.calebderosier.playlistfeed.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.calebderosier.playlistfeed.R
import com.calebderosier.playlistfeed.data.models.Playlist
import com.calebderosier.playlistfeed.data.models.Song
import com.calebderosier.playlistfeed.extensions.ctx
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.song_item.view.*

class SongListAdapter(private val playlist: Playlist?) : RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val songView = LayoutInflater.from(parent.ctx).inflate(R.layout.song_item, parent, false)
        return SongViewHolder(songView)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        if (playlist?.tracks?.data == null) return
        holder.bindPlaylist(playlist.tracks.data[position])
    }

    override fun getItemCount(): Int = (playlist?.nb_tracks ?: 1) - 1

    class SongViewHolder(val songView: View) : RecyclerView.ViewHolder(songView) {
        fun bindPlaylist(song: Song?) {
            Picasso.get().load(song?.album?.cover_small).into(songView.songArtwork)

            songView.songTitle.text = song?.title ?: ""
            songView.songArtist.text = song?.artist?.name ?: ""

        }
    }
}