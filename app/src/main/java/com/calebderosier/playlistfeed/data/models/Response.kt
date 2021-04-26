package com.calebderosier.playlistfeed.data.models

import com.google.gson.annotations.SerializedName

data class Playlist(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("duration")
    val duration: Int,
    @SerializedName("public")
    val isPublic: Boolean,
    @SerializedName("is_loved_track")
    val isLovedTrack: Boolean,
    @SerializedName("collaborative")
    val isCollaborative: Boolean,
    @SerializedName("nb_tracks")
    val numberOfTracks: Int,
    @SerializedName("fans")
    val numberOfFans: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("share")
    val share: String,
    @SerializedName("picture")
    val picture: String,
    @SerializedName("picture_small")
    val pictureSmall: String,
    @SerializedName("picture_medium")
    val pictureMedium: String,
    @SerializedName("picture_big")
    val pictureLarge: String,
    @SerializedName("picture_xl")
    val pictureXL: String,
    @SerializedName("checksum")
    val checksum: String,
    @SerializedName("tracklist")
    val trackList: String,
    @SerializedName("creation_date")
    val creationDate: String,
    @SerializedName("md5_image")
    val md5: String,
    @SerializedName("picture_type")
    val pictureType: String,
    @SerializedName("creator")
    val creator: Creator,
    @SerializedName("type")
    val type: String,
    @SerializedName("tracks")
    val tracks: TrackList,
)

data class Creator(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("tracklist")
    val trackList: String,
    @SerializedName("type")
    val type: String
)

data class TrackList(
    @SerializedName("data")
    val data: List<Song>?,
    @SerializedName("checksum")
    val checksum: String
)

data class Song(
    @SerializedName("id")
    val id: String,
    @SerializedName("readable")
    val readable: Boolean,
    @SerializedName("title")
    val title: String,
    @SerializedName("title_short")
    val titleShort: String,
    @SerializedName("title_version")
    val titleVersion: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("rank")
    val rank: String,
    @SerializedName("explicit_lyrics")
    val isExplicit: Boolean,
    @SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Int,
    @SerializedName("explicit_content_cover")
    val explicitContentCover: Int,
    @SerializedName("preview")
    val preview: String,
    @SerializedName("md5_image")
    val md5: String,
    @SerializedName("time_add")
    val timeAdd: Int,
    @SerializedName("artist")
    val artist: Artist?,
    @SerializedName("album")
    val album: Album?,
    @SerializedName("type")
    val type: String
)

data class Artist(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("tracklist")
    val trackList: String,
    @SerializedName("type")
    val type: String
)

data class Album(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("cover")
    val cover: String,
    @SerializedName("cover_small")
    val coverSmall: String,
    @SerializedName("cover_medium")
    val coverMedium: String,
    @SerializedName("cover_big")
    val coverLarge: String,
    @SerializedName("cover_xl")
    val coverXL: String,
    @SerializedName("md5_image")
    val md5: String,
    @SerializedName("tracklist")
    val trackList: String,
    @SerializedName("type")
    val type: String
)