package com.calebderosier.playlistfeed.dagger.modules

import com.calebderosier.playlistfeed.data.api.DeezerService
import com.calebderosier.playlistfeed.data.api.PlaylistRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideDeezerService(): DeezerService {
        return Retrofit.Builder()
            .baseUrl("https://api.deezer.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeezerService::class.java)
    }

    @Singleton
    @Provides
    fun providePlaylistRepository(deezerService: DeezerService): PlaylistRepository {
        return PlaylistRepository(deezerService)
    }
}