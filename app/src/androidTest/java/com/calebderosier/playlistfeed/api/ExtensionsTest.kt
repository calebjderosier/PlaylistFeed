package com.calebderosier.playlistfeed.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.calebderosier.playlistfeed.util.secsToMinsAndSecsStr
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ExtensionsTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    /**
    * Test the secsToMinsAndSecsStr() function in Extensions.kt
     */
    @Test
    fun testDurationConversionFunction() {
        Assert.assertEquals("200".secsToMinsAndSecsStr(), "3m20s")
        Assert.assertEquals("180".secsToMinsAndSecsStr(), "3m0s")
        Assert.assertEquals("0".secsToMinsAndSecsStr(), "0m0s")
        Assert.assertEquals("-1".secsToMinsAndSecsStr(), "0m0s")
        Assert.assertEquals("-10000".secsToMinsAndSecsStr(), "0m0s")
    }
}