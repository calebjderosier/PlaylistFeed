package com.calebderosier.playlistfeed.dagger

import android.app.Application
import com.calebderosier.playlistfeed.PlaylistFeedApp
import com.calebderosier.playlistfeed.dagger.modules.AppModule
import com.calebderosier.playlistfeed.dagger.modules.MainActivityModule
import com.calebderosier.playlistfeed.dagger.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        MainActivityModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: PlaylistFeedApp)
}