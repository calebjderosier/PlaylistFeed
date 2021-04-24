package com.calebderosier.playlistfeed.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SongDetails(
    var title: String?,
    var artist: String?,
    var albumArtworkUrl: String?,
    var duration: String?,
    var isExplicit: Boolean
): Parcelable