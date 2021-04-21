package com.calebderosier.playlistfeed.dagger

import com.calebderosier.playlistfeed.ui.views.DetailsActivity
import com.calebderosier.playlistfeed.ui.views.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailsActivity(): DetailsActivity
}