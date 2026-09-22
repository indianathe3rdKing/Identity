package com.indianathe3rd.identity.domain.usecase.usage

import com.indianathe3rd.identity.domain.model.AppUsage
import com.indianathe3rd.identity.domain.repository.UsageRepository

class GetAppsUsageUsecase(
    private val repository: UsageRepository,
) {
    suspend operator fun invoke(beginTime: Long,endTime: Long,intervalType:Int):List<AppUsage> = repository.getAppsUsage(beginTime,endTime,intervalType)
}