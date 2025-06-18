package com.example.localchat

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var service: UdpBroadcastService
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.messageList)
        val input = findViewById<EditText>(R.id.messageInput)
        val send = findViewById<Button>(R.id.sendButton)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listView.adapter = adapter

        service = UdpBroadcastService(9999)
        service.startListening { onMessageReceived(it) }

        send.setOnClickListener {
            val text = input.text.toString()
            if (text.isNotEmpty()) {
                service.send(text)
                onMessageReceived(text)
                input.text.clear()
            }
        }
    }

    fun onMessageReceived(message: String) {
        runOnUiThread { adapter.add(message) }
    }

    override fun onDestroy() {
        super.onDestroy()
        service.stop()
    }
}
