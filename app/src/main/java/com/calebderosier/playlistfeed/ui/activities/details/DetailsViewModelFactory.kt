package com.calebderosier.playlistfeed.ui.activities.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.calebderosier.playlistfeed.ui.activities.main.MainViewModel

class DetailsViewModelFactory(): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailsViewModel::class.java)){
            return DetailsViewModel() as T
        }
        throw IllegalArgumentException ("UnknownViewModel")
    }

}