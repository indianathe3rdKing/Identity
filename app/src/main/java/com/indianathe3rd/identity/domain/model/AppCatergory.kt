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

data class ClassifiedAppUsage(
    val packageName:  String,
    val appName: String,
    val totalTimeInForeground: Long,
    val category: AppCategory
)

data class CategoryUsage(
    val category: AppCategory,
    val totalTime: Long,
    val percentage: Float
)