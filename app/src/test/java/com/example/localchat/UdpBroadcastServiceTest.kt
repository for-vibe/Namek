package com.example.localchat

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class UdpBroadcastServiceTest {
    @Test
    fun sendAndReceive() = runBlocking {
        val service = UdpBroadcastService(9999)
        var received: String? = null
        service.startListening { message ->
            received = message
        }
        service.send("hello")
        delay(100)
        service.stop()
        assertEquals("hello", received)
    }
}
