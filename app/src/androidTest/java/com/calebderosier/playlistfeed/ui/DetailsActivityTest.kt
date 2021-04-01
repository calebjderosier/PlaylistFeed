package com.calebderosier.playlistfeed.ui

import android.content.Context
import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.calebderosier.playlistfeed.ui.activities.details.DetailsActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test for DetailsActivity, which will execute on an Android device.
 */
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class DetailsActivityTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var activityScenario: ActivityScenario<DetailsActivity>

    @Before
    fun before() = runBlocking {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = Intent(context, DetailsActivity::class.java)
        activityScenario = ActivityScenario.launch(intent)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.calebderosier.playlistfeed", appContext.packageName)
    }

    /*
    * Test parseLength() function in DetailsActivity, ensure it returns expected results
     */
    @Test
    fun testParseLengthFunction() {
        activityScenario.onActivity {
            assertEquals(it.parseLength(200), "3m20s")
            assertEquals(it.parseLength(180), "3m0s")
            assertEquals(it.parseLength(0), "0m0s")
            assertEquals(it.parseLength(-1), "0m0s")
            assertEquals(it.parseLength(-10000), "0m0s")
        }
    }
}