package com.indianathe3rd.identity.domain.usecase.usage

import android.util.Log
import com.indianathe3rd.identity.domain.model.CategoryUsage
import com.indianathe3rd.identity.domain.model.ClassifiedAppUsage
import javax.inject.Inject

class GetCategoryUsageSummaryUsecase @Inject constructor(
    private val getAppsUsageUsecase: GetAppsUsageUsecase,
    private val getClassifiedAppUsecase: GetClassifiedAppsUsageUsecase
) {
    suspend operator fun invoke(
        beginTime: Long,
        endTime: Long,
        intervalType: Int,
    ): List<CategoryUsage> {

        val appsUsage = getAppsUsageUsecase(beginTime, endTime, intervalType)
        val classifiedAppsUsage = appsUsage.map { app ->
            getClassifiedAppUsecase(app.packageName)

            ClassifiedAppUsage(
                packageName = app.packageName,
                appName = app.appName,
                totalTimeInForeground = app.totalTimeInForeground,
                category = getClassifiedAppUsecase(app.packageName)
            )
        }
        Log.e(TAG, "Classified Apps Usage: $classifiedAppsUsage")

        val categoryMap = classifiedAppsUsage
            .groupBy { it.category }
            .mapValues { (_, apps) ->
                apps.sumOf { it.totalTimeInForeground }
            }

        val categories = categoryMap.map { (category, totalTime) ->
            val percentage = if (totalTime > 0) (totalTime.toFloat() / totalTime) * 100f else 0f
            CategoryUsage(
                category = category.category,
                totalTime = totalTime,
                percentage = percentage
            )
        }

        return categories
    }

    companion object{
        private const val TAG = "GetCategoryUsageSummaryUsecase"
    }
}