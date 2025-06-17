package com.example.localchat

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.ext.junit.runners.AndroidJUnit4

@RunWith(AndroidJUnit4::class)
class UdpBroadcastServiceE2ETest {
    @Test
    fun sendAndReceive() = runBlocking {
        val service = UdpBroadcastService(9999, java.net.InetAddress.getByName("127.0.0.1"))
        var received: String? = null
        service.startListening { received = it }
        service.send("ping")
        delay(100)
        service.stop()
        assertEquals("ping", received)
    }
}
