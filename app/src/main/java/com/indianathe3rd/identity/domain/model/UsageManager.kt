package com.indianathe3rd.identity.domain.model

import android.graphics.drawable.Drawable

data class AppUsage(
    val packageName: String,
    val appName: String,
    val totalTimeInForeground: Long,
    val lastTimeUsed: Long,

)

data class UsageSummary(
    val totalScreenTime: Long,
    val apps: List<AppUsage>,

)

data class DailyUsage(
    val date: String,
    val totalTime: Long,
    val apps: List<AppUsage>
)