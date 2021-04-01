package com.calebderosier.playlistfeed.ui

import android.content.Context
import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.calebderosier.playlistfeed.ui.activities.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test for MainActivity, which will execute on an Android device.
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun before() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = Intent(context, MainActivity::class.java)
        activityScenario = ActivityScenario.launch(intent)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.calebderosier.playlistfeed", appContext.packageName)
    }

    /*
    * Verify that RecyclerView adapter is not defined on initial creation of class
     */
    @Test
    fun verifyAdapterIsNull() {
        activityScenario.onActivity {
            assertEquals(null, it.rv_song_list.adapter)
        }
    }
}