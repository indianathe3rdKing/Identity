package com.indianathe3rd.identity.data.mapper


object AppUsageMapper{

    fun Long.formatUsageTime(): String {
        val totalSeconds = this/1000
        val seconds = totalSeconds % 60
        val minutes = (totalSeconds / 60) % 60
        val hours = totalSeconds / 3600

        return when {
            hours > 0 -> "${hours}h ${minutes}m"
            minutes > 0 -> "${minutes}m"
            else -> "${seconds}s"
        }

    }
}