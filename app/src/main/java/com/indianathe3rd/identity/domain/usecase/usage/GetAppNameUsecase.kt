package com.indianathe3rd.identity.domain.usecase.usage

import com.indianathe3rd.identity.domain.repository.UsageRepository
import javax.inject.Inject

class GetAppNameUsecase @Inject constructor(
    private val repository : UsageRepository
) {
    suspend operator fun invoke(packageName: String): String = repository.getAppName(packageName)
}