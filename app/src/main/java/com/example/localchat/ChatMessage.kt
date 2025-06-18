package com.example.localchat

import org.json.JSONObject

/** Simple data model for chat messages. */
data class ChatMessage(
    val name: String?,
    val text: String,
    val timestamp: Long
) {
    fun toJson(): String = JSONObject().apply {
        put("name", name)
        put("text", text)
        put("time", timestamp)
    }.toString()

    companion object {
        fun fromJson(json: String): ChatMessage {
            return try {
                val obj = JSONObject(json)
                ChatMessage(
                    obj.optString("name", null),
                    obj.getString("text"),
                    obj.optLong("time", System.currentTimeMillis())
                )
            } catch (e: Exception) {
                // Fallback for plain text messages
                ChatMessage(null, json, System.currentTimeMillis())
            }
        }
    }
}
