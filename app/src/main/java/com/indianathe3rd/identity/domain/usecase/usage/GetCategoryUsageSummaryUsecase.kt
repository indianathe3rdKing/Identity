package com.indianathe3rd.identity.domain.usecase.usage

import android.util.Log
import com.indianathe3rd.identity.domain.model.CategoryUsage
import com.indianathe3rd.identity.domain.model.ClassifiedAppUsage
import com.indianathe3rd.identity.domain.repository.ClassificationRepository
import javax.inject.Inject

class GetCategoryUsageSummaryUsecase @Inject constructor(
    private val getAppsUsageUsecase: GetAppsUsageUsecase,
    private val getClassifiedAppUsecase: GetClassifiedAppsUsageUsecase,
    private val classifyRepository: ClassificationRepository
) {
    suspend operator fun invoke(
        beginTime: Long,
        endTime: Long,
        intervalType: Int,
    ): List<CategoryUsage> {
        Log.e(
            TAG,
            "Fetching apps usage from $beginTime to $endTime with interval type $intervalType"
        )
        val appsUsage = getAppsUsageUsecase(beginTime, endTime, intervalType)
        val classifiedAppsUsage = appsUsage.map { app ->
              Log.e(TAG, "Staring classification of app summary")
            classifyRepository.createClassification(app.packageName)
              Log.e(TAG, "Classified App: $app")
            ClassifiedAppUsage(
                packageName = app.packageName,
                appName = app.appName,
                totalTimeInForeground = app.totalTimeInForeground,
                category = getClassifiedAppUsecase(app.packageName)
            )
        }
        val testApp = appsUsage.take(2) // Take the first 2 apps for testing

        testApp.forEach { app ->
            try {
                val classifiedApp = getClassifiedAppUsecase(app.packageName)
                Log.e(TAG, "Test App: $classifiedApp")
            } catch (e: Exception) {
                Log.e(TAG, "Error classifying app ${app.packageName}: ${e.message}")
            }
        }

        appsUsage.firstOrNull { app ->
            try {
                val classifiedApp = getClassifiedAppUsecase(app.packageName)
                Log.e(TAG, "Test App: $classifiedApp")
                true
            } catch (e: Exception) {
                Log.e(TAG, "Error classifying app ${app.packageName}: ${e.message}")
                false
            }
        }
        Log.e(TAG, "It ran successfully")

//        val categoryMap = classifiedAppsUsage
//            .groupBy { it.category }
//            .mapValues { (_, apps) ->
//                apps.sumOf { it.totalTimeInForeground }
//            }
//
//        val categories = categoryMap.map { (category, totalTime) ->
//            val percentage = if (totalTime > 0) (totalTime.toFloat() / totalTime) * 100f else 0f
//            CategoryUsage(
//                category = category.category,
//                totalTime = totalTime,
//                percentage = percentage
//            )
//        }

        return emptyList() // Return the list of CategoryUsage objects
    }

    companion object {
        private const val TAG = "GetCategoryUsageSummaryUsecase"
    }
}