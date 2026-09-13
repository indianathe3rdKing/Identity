package com.indianathe3rd.identity.domain.repository

import android.app.usage.UsageStatsManager
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.vector.ImageVector
import com.indianathe3rd.identity.domain.model.AppUsage

interface UsageRepository {
    suspend fun getAppsUsage(beginTime: Long,endTime: Long,intervalType:Int): List<AppUsage>
    suspend fun getAppName(packageName:String): String
    suspend fun getAppIcon(packageName: String): Drawable
}