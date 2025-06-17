package com.example.localchat

import android.app.Application
import android.content.Intent
import android.os.Handler
import android.os.Looper

class LocalChatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler { _, throwable ->
            val intent = Intent(this, CrashActivity::class.java).apply {
                putExtra(CrashActivity.EXTRA_ERROR, throwable.stackTraceToString())
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
            startActivity(intent)
            Handler(Looper.getMainLooper()).postDelayed({
                android.os.Process.killProcess(android.os.Process.myPid())
            }, 1000)
        }
    }
}
