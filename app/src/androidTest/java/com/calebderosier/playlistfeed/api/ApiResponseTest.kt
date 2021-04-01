package com.calebderosier.playlistfeed.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.calebderosier.playlistfeed.data.api.PlaylistRetriever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ApiResponseTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    /*
    * Verify that the Deezer API result is not null
     */
    @Test
    fun verifyResultIsNotNull() = runBlocking {
        Assert.assertNotNull(PlaylistRetriever.retrievePlaylist())
    }
}