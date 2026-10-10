package com.indianathe3rd.identity.di

import android.content.Context
import com.indianathe3rd.identity.data.Config
import com.indianathe3rd.identity.data.repository.ClassificationRepositoryImpl
import com.indianathe3rd.identity.data.repository.UsageRepositoryImpl
import com.indianathe3rd.identity.domain.repository.ClassificationRepository
import com.indianathe3rd.identity.domain.repository.UsageRepository
import com.indianathe3rd.identity.domain.usecase.usage.GetAppNameUsecase
import com.indianathe3rd.identity.domain.usecase.usage.GetAppsUsageUsecase
import com.indianathe3rd.identity.domain.usecase.usage.GetCategoryUsageSummaryUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
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
    fun provideClassificationRepository(
        getAppNameUsecase: GetAppNameUsecase
    ): ClassificationRepository{
        return ClassificationRepositoryImpl(getAppNameUsecase)
    }

    @Provides
    @Singleton
    fun provideGetAppName(repository: UsageRepository): GetAppNameUsecase{
        return GetAppNameUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAppsUsecase(repository: UsageRepository): GetAppsUsageUsecase{
        return GetAppsUsageUsecase(repository)
    }

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient{
        return createSupabaseClient(
            supabaseUrl = Config.SUPABASE_URL,
            supabaseKey = Config.SUPABASE_KEY
        ){
            install(Postgrest)
            install(Auth)
        }
    }

    @Provides
    @Singleton
    fun provideSupabaseDatabase(client: SupabaseClient): Postgrest{
        return client.postgrest
    }

    @Provides
    @Singleton
    fun provideSupabaseAuth(client: SupabaseClient): Auth{
        return client.auth
    }


}