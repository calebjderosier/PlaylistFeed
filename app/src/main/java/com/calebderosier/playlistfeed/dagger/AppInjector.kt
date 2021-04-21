package com.calebderosier.playlistfeed.dagger

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.calebderosier.playlistfeed.PlaylistFeedApp
import dagger.android.AndroidInjection

object AppInjector {
    fun init(playlistFeedApp: PlaylistFeedApp) {
        DaggerAppComponent.builder().application(playlistFeedApp)
            .build().inject(playlistFeedApp)

        playlistFeedApp
            .registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    handleActivity(activity)
                }

                override fun onActivityStarted(activity: Activity) {

                }

                override fun onActivityResumed(activity: Activity) {

                }

                override fun onActivityPaused(activity: Activity) {

                }

                override fun onActivityStopped(activity: Activity) {

                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

                }

                override fun onActivityDestroyed(activity: Activity) {

                }
            })
    }

    private fun handleActivity(activity: Activity) {
        AndroidInjection.inject(activity)
    }
}