package com.example.localchat

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class CrashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crash)

        val errorText = intent.getStringExtra(EXTRA_ERROR) ?: "Unknown error"
        findViewById<TextView>(R.id.crashMessage).text = errorText

        findViewById<Button>(R.id.copyButton).setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(ClipData.newPlainText("error", errorText))
        }

        findViewById<Button>(R.id.closeButton).setOnClickListener {
            finish()
            exitProcess(1)
        }
    }

    companion object {
        const val EXTRA_ERROR = "error"
    }
}
