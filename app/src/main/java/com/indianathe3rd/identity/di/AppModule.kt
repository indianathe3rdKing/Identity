package com.indianathe3rd.identity.di

import android.content.Context
import com.indianathe3rd.identity.data.repository.UsageRepositoryImpl
import com.indianathe3rd.identity.domain.repository.UsageRepository
import com.indianathe3rd.identity.domain.usecase.usage.GetAppsUsageUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideUsageRepository(
        @ApplicationContext context: Context
    ): UsageRepository {
        return UsageRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideGetAppsUsecase(repository: UsageRepository): GetAppsUsageUsecase{
        return GetAppsUsageUsecase(repository)
    }

}