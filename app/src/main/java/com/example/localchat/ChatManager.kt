package com.example.localchat

class ChatManager(private val service: UdpBroadcastService) {
    private val _messages = mutableListOf<String>()
    val messages: List<String> get() = _messages

    fun start() {
        service.startListening { msg -> _messages.add(msg) }
    }

    fun stop() {
        service.stop()
    }

    fun send(message: String) {
        service.send(message)
        _messages.add(message)
    }
}
