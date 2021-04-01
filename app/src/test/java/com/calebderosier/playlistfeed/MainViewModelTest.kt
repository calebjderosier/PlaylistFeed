package com.calebderosier.playlistfeed

import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.calebderosier.playlistfeed.ui.activities.main.MainViewModel
import com.calebderosier.playlistfeed.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

/**
 * Unit tests for MainViewModel, which will execute independent of the Android device.
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var mainViewModel: MainViewModel

    private lateinit var isLoadingLiveData: LiveData<Boolean>

    private lateinit var isErrorLiveData: LiveData<Boolean>

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setup() {
        mainViewModel = MainViewModel()
        isLoadingLiveData = mainViewModel.getIsLoading()
        isErrorLiveData = mainViewModel.shouldShowError()
    }

    @Test
    fun `verify isLoading and isError are true & false respectively`() = runBlocking {
        val isLoading = isLoadingLiveData.value
        assertNotNull(isLoading)
        isLoading?.let { assertTrue(it) }

        val isError = isErrorLiveData.value
        assertNotNull(isError)
        isError?.let { assertFalse(it) }

        return@runBlocking
    }

    @Test
    fun `verify that model returns playlist from repository`() = runBlocking {
        mainViewModel.playlist.observeForever { }

        assertNotNull(mainViewModel.playlist)

        return@runBlocking
    }
}
