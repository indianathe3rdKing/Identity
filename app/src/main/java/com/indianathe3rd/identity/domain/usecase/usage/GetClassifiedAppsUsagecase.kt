package com.indianathe3rd.identity.domain.usecase.usage

import com.indianathe3rd.identity.domain.repository.ClassificationRepository
import javax.inject.Inject

class GetClassifiedAppsUsageUsecase @Inject constructor(
    private val repository: ClassificationRepository
) {
    suspend operator fun invoke(packageName: String) = repository.createClassification(packageName)
}