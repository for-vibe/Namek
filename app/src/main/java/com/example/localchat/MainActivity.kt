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

        val listView = findViewById<ListView>(R.id.messagesList)
        val input = findViewById<EditText>(R.id.messageInput)
        val button = findViewById<Button>(R.id.sendButton)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listView.adapter = adapter

        service = UdpBroadcastService(9999)
        service.startListening { message ->
            runOnUiThread { adapter.add(message) }
        }

        button.setOnClickListener {
            val text = input.text.toString()
            if (text.isNotBlank()) {
                service.send(text)
                adapter.add(text)
                input.text.clear()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        service.stop()
    }
}
