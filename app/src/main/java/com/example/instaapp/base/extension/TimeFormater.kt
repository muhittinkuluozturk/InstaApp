package com.example.instaapp.base.extension

import android.annotation.SuppressLint
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object TimeFormater {
    @SuppressLint("NewApi")
    fun formatTimestamp(timestamp: String): String {
        val instant = Instant.ofEpochSecond(timestamp.toLong())
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            .withZone(ZoneId.systemDefault())
        return formatter.format(instant)
    }
}