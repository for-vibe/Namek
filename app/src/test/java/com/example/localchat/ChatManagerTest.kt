package com.example.localchat

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import java.net.InetAddress

class ChatManagerTest {
    @Test
    fun sendAndReceive() = runBlocking {
        val service = UdpBroadcastService(9999, InetAddress.getByName("127.0.0.1"))
        val manager = ChatManager(service)
        manager.start()
        manager.send("test")
        delay(100)
        manager.stop()
        assertTrue(manager.messages.any { it == "test" })
    }
}
