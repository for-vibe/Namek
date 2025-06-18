package com.example.localchat

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import java.net.InetAddress
import org.junit.Assert.assertNull

@RunWith(AndroidJUnit4::class)
class SendFromMainThreadTest {
    @Test
    fun sendDoesNotThrow() {
        val service = UdpBroadcastService(9999, InetAddress.getByName("127.0.0.1"))
        var exception: Exception? = null
        try {
            service.send("ping")
        } catch (e: Exception) {
            exception = e
        } finally {
            service.stop()
        }
        assertNull(exception)
    }
}
