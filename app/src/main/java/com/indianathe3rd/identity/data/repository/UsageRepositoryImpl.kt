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

        val stats = usageStatsManager.queryUsageStats(
            UsageStatsManager.INTERVAL_DAILY,
            beginTime,
            endTime
        ) ?: return emptyList()


        return stats
            .filter { it.totalTimeInForeground > 0 }
            .sortedByDescending { it.totalTimeInForeground }
            .map{stats ->
                AppUsage(
                    packageName = stats.packageName,
                    appName = getAppName(stats.packageName),
                    totalTimeInForeground = stats.totalTimeInForeground,
                    lastTimeUsed = stats.lastTimeUsed,

                )
            }
    }

    override suspend fun getAppName(packageName: String): String {
        return try {
            val packageManager = context.packageManager
            val appInfo  = packageManager.getApplicationInfo(packageName,0)
            packageManager.getApplicationLabel(appInfo)
        } catch (e: Exception) {
            Log.d(TAG,"${packageName} : ${e}")
        } as String
    }

    override suspend fun getAppIcon(packageName: String): Drawable {
        return try {
            val packageManager = context.packageManager
            val appInfo = packageManager.getApplicationInfo(packageName,0)
            packageManager.getApplicationIcon(appInfo)
        }catch (e: Exception){
            Log.d(TAG, "Failed to get icon for $packageName: $e")
            null
        } as Drawable
    }


    companion object {
        private val TAG = "UsageManager"
    }
}