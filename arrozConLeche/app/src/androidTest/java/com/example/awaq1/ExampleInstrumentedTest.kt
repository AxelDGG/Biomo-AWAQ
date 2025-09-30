package com.example.awaq1

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun useAppContext() {
        assertEquals("com.example.awaq1", context.packageName)
    }

    @Test
    fun verifySecretsExist() {
        val domainResId = context.resources.getIdentifier("com_auth0_domain", "string", context.packageName)
        val clientIdResId = context.resources.getIdentifier("com_auth0_client_id", "string", context.packageName)
        assertNotEquals("Auth0 domain not found! (Missing secrets for project)", 0, domainResId)
        assertNotEquals("Auth0 client ID not found! (Missing secrets for project)", 0, clientIdResId)
    }
}