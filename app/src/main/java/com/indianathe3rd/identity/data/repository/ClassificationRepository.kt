package com.indianathe3rd.identity.data.repository

import com.indianathe3rd.identity.domain.model.AppClassification
import com.indianathe3rd.identity.domain.repository.ClassificationRepository

class ClassificationRepositoryImpl : ClassificationRepository{
    override suspend fun getClassifications(packageNames: List<String>): List<AppClassification> {
        TODO("Not yet implemented")
    }

    override suspend fun saveClassification(classification: AppClassification) {
        TODO("Not yet implemented")
    }

    override suspend fun createClassification(packageNames: List<String>): List<AppClassification> {

    }
}