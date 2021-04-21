package com.calebderosier.playlistfeed.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.calebderosier.playlistfeed.data.models.Playlist
import com.calebderosier.playlistfeed.data.models.Song
import com.calebderosier.playlistfeed.databinding.SongItemBinding
import com.calebderosier.playlistfeed.ui.views.DetailsActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class SongListAdapter(private val playlist: Playlist?) : RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val itemBinding = SongItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        if (playlist?.tracks?.data == null) return
        holder.itemBinding.llSongRow.setOnClickListener {
            val context = it.context
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("songJson", Gson().toJson(playlist.tracks.data[position]))
            context.startActivity(intent)
        }
        holder.bindPlaylist(playlist.tracks.data[position])
    }

    override fun getItemCount(): Int = (playlist?.numberOfTracks ?: 0)

    class SongViewHolder(val itemBinding: SongItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindPlaylist(song: Song?) {
            Picasso.get().load(song?.album?.coverSmall).into(itemBinding.ivSongArtwork)

            itemBinding.tvSongTitle.text = song?.title ?: ""
            itemBinding.tvSongArtist.text = song?.artist?.name ?: ""
        }
    }
}