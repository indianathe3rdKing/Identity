package com.indianathe3rd.identity.domain.usecase.usage

import com.indianathe3rd.identity.domain.repository.UsageRepository

class GetAppNameUsecase(
    private val repository : UsageRepository
) {
    suspend operator fun invoke(packageName: String): String = repository.getAppName(packageName)
}