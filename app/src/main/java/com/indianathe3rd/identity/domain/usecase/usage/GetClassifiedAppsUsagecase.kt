package com.indianathe3rd.identity.domain.usecase.usage

import android.util.Log
import com.indianathe3rd.identity.domain.model.AppClassification
import com.indianathe3rd.identity.domain.repository.ClassificationRepository
import javax.inject.Inject

class GetClassifiedAppsUsageUsecase @Inject constructor(
    private val repository: ClassificationRepository
) {
    suspend operator fun invoke(packageName: String): AppClassification {
        return try {
            val result = repository.createClassification(packageName)
            Log.e(TAG, "UseCase SUCCESS: $result")
            result
        } catch (e: Exception) {
            Log.e(TAG, "UseCase FAILED", e)
            throw e
        }
    }

    companion object {
        private const val TAG = "GetClassifiedAppsUsecase"
    }
}