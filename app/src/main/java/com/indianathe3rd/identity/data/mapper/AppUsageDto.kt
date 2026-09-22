package com.indianathe3rd.identity.data.mapper


object AppUsageMapper{

    fun Long.formatUsageTime(): String {
        val seconds = this /1000
        val minutes = seconds / 60
        val hours = minutes / 60

        return when {
            hours > 0 -> "${hours}h ${minutes}m"
            minutes > 0 -> "${minutes}m"
            else -> "${seconds}s"
        }

    }
}