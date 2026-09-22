package com.indianathe3rd.identity.data.repository

import android.app.usage.UsageStatsManager
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.ui.graphics.vector.ImageVector
import com.indianathe3rd.identity.domain.model.AppUsage
import com.indianathe3rd.identity.domain.repository.UsageRepository

class UsageRepositoryImpl(
    private val context: Context
) : UsageRepository {
    override suspend fun getAppsUsage(
        beginTime: Long,
        endTime: Long,
        intervalType: Int,
    ): List<AppUsage> {

        val usageStatsManager = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

        val stats = usageStatsManager.queryAndAggregateUsageStats(
            beginTime,
            endTime
        )

        return stats.values
            .filter { it.totalTimeInForeground > 0 }
            .sortedByDescending { it.totalTimeInForeground }
            .map { usageStats ->
                AppUsage(
                    packageName = usageStats.packageName,
                    appName = getAppName(usageStats.packageName),
                    totalTimeInForeground = usageStats.totalTimeInForeground,
                    lastTimeUsed = usageStats.lastTimeUsed,
                )
            }
    }

    override suspend fun getAppName(packageName: String): String {
        return try {
            val packageManager = context.packageManager
            val appInfo  = packageManager.getApplicationInfo(packageName,0)
            packageManager.getApplicationLabel(appInfo).toString()
        } catch (e: Exception) {
            Log.d(TAG,"${packageName} : ${e}")
            packageName
        }
    }



    companion object {
        private val TAG = "UsageManager"
    }
}