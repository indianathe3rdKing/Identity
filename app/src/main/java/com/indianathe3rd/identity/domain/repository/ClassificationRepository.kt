package com.indianathe3rd.identity.domain.repository

import com.indianathe3rd.identity.domain.model.AppClassification

interface ClassificationRepository {
    suspend fun getClassifications(
        packageNames: List<String>
    ): List<AppClassification>

    suspend fun saveClassification(
        classification: AppClassification
    )

    suspend fun createClassification(packageName: String): AppClassification
}