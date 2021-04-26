package com.calebderosier.playlistfeed.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.calebderosier.playlistfeed.data.api.DeezerService
import com.calebderosier.playlistfeed.data.api.PlaylistRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ApiResponseTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val deezerService = mock(DeezerService::class.java)
    private var playlistRepository = PlaylistRepository(deezerService)

    /**
    * Verify that the Deezer API result is not null
     */
    @Test
    fun verifyResultIsNotNull() = runBlocking {
        Assert.assertNotNull(playlistRepository.retrievePlaylist())
    }
}