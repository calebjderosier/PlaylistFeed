package com.calebderosier.playlistfeed.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.lang.Integer.parseInt

/**
 * Extension that returns a string representing the given duration in mins and secs with format XXmXXs
 */
fun String.secsToMinsAndSecsStr(): String {
    try {
        val l = this.toInt()
        if (l > 0) return "${l / 60}m${l % 60}s"
    } catch (exception: NumberFormatException) {
        return "0m0s"
    }
    return "0m0s"
}

/**
 * Return a LiveData object from MutableLiveData
 */
fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>