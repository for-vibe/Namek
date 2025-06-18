package com.example.localchat

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import java.net.InetAddress
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var service: UdpBroadcastService
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var prefs: SharedPreferences
    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.messageList)
        val input = findViewById<EditText>(R.id.messageInput)
        val send = findViewById<Button>(R.id.sendButton)
        val menu = findViewById<Button>(R.id.menuButton)
        drawer = findViewById(R.id.drawerLayout)
        val nameInput = findViewById<EditText>(R.id.nameInput)
        val saveName = findViewById<Button>(R.id.saveNameButton)

        prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        nameInput.setText(prefs.getString("name", ""))

        menu.setOnClickListener { drawer.openDrawer(Gravity.END) }
        saveName.setOnClickListener {
            prefs.edit().putString("name", nameInput.text.toString()).apply()
            drawer.closeDrawer(Gravity.END)
        }

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        listView.adapter = adapter

        if (prefs.getString("name", null).isNullOrEmpty()) {
            drawer.openDrawer(Gravity.END)
        }

        service = UdpBroadcastService(9999)
        service.startListening { address, text -> onMessageReceived(address, text) }

        send.setOnClickListener {
            val text = input.text.toString()
            if (text.isNotEmpty()) {
                val message = ChatMessage(prefs.getString("name", null), text, System.currentTimeMillis())
                val json = message.toJson()
                service.send(json)
                onMessageReceived(InetAddress.getLoopbackAddress(), json)
                input.text.clear()
            }
        }
    }

    fun onMessageReceived(address: InetAddress, json: String) {
        val msg = ChatMessage.fromJson(json)
        val name = msg.name?.takeIf { it.isNotBlank() } ?: address.hostAddress
        val time = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date(msg.timestamp))
        val display = "$name: ${'$'}{msg.text} [${'$'}time]"
        runOnUiThread { adapter.add(display) }
    }

    override fun onDestroy() {
        super.onDestroy()
        service.stop()
    }
}
