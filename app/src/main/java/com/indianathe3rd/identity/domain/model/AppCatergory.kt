package com.indianathe3rd.identity.domain.model

enum class AppCategory {
    SOCIAL,
    ENTERTAINMENT,
    GAMING,
    PRODUCTIVITY,
    READING,
    COMMUNICATION,
    UTILITY,
    OTHER
}

data class AppClassification(
    val packageName: String,
    val appName: String?,
    val category: AppCategory
)
data class ClassifiedAppUsage(
    val packageName:  String,
    val appName: String,
    val totalTimeInForeground: Long,
    val category: AppClassification
)

data class CategoryUsage(
    val category: AppCategory,
    val totalTime: Long,
    val percentage: Float
)

data class ScreenTimeReport(
    val totalScreenTime: Long,

)

data class UsageReport(
    val period: TimePeriod,
    val totalTime: Long,
    val categories: List<CategoryUsage>,
    val startTime: Long,
    val endTime: Long
)

enum class TimePeriod{
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY
}