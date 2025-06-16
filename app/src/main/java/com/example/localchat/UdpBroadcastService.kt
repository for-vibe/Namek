package com.example.localchat

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.net.InetSocketAddress

/**
 * Simple service for sending and receiving UDP broadcast messages within the local network.
 */
class UdpBroadcastService(private val port: Int) {
    private val socket = DatagramSocket(null)
    private var job: Job? = null

    init {
        socket.reuseAddress = true
        socket.bind(InetSocketAddress(port))
    }

    fun send(message: String) {
        val data = message.toByteArray()
        val packet = DatagramPacket(data, data.size, InetAddress.getByName("255.255.255.255"), port)
        socket.send(packet)
    }

    fun startListening(onMessage: (String) -> Unit) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val buffer = ByteArray(1024)
            while (true) {
                val packet = DatagramPacket(buffer, buffer.size)
                socket.receive(packet)
                val text = String(packet.data, 0, packet.length)
                onMessage(text)
            }
        }
    }

    fun stop() {
        job?.cancel()
        socket.close()
    }
}
