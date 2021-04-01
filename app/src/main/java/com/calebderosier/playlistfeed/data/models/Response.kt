package com.calebderosier.playlistfeed.data.models

data class Playlist(
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val duration: Int = 0,
    val public: Boolean = false,
    val is_loved_track: Boolean = false,
    val collaborative: Boolean = false,
    val nb_tracks: Int = 0,
    val fans: Int = 0,
    val link: String = "",
    val share: String = "",
    val picture: String = "",
    val picture_small: String = "",
    val picture_medium: String = "",
    val picture_big: String = "",
    val picture_xl: String = "",
    val checksum: String = "",
    val tracklist: String = "",
    val creation_date: String = "",
    val md5_image: String = "",
    val picture_type: String = "",
    val creator: Creator,
    val type: String = "",
    val tracks: TrackList,
)

data class Creator(
    val id: String = "",
    val name: String = "",
    val tracklist: String = "",
    val type: String = ""
)

data class TrackList(
    val data: List<Song>?,
    val checksum: String = ""
)

data class Song(
    val id: String = "",
    val readable: Boolean = false,
    val title: String = "",
    val title_short: String = "",
    val title_version: String = "",
    val link: String = "",
    val duration: String = "",
    val rank: String = "",
    val explicit_lyrics: Boolean = false,
    val explicit_content_lyrics: Int = 0,
    val explicit_content_cover: Int = 0,
    val preview: String = "",
    val md5_image: String = "",
    val time_add: Int = 0,
    val artist: Artist?,
    val album: Album?,
    val type: String = ""
)

data class Artist(
    val id: String = "",
    val name: String = "",
    val link: String = "",
    val tracklist: String = "",
    val type: String = ""
)

data class Album(
    val id: String = "",
    val title: String = "",
    val cover: String = "",
    val cover_small: String = "",
    val cover_medium: String = "",
    val cover_big: String = "",
    val cover_xl: String = "",
    val md5_image: String = "",
    val tracklist: String = "",
    val type: String = ""
)