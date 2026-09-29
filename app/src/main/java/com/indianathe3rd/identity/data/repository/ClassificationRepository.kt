package com.indianathe3rd.identity.data.repository

import android.util.Log
import com.google.ai.client.generativeai.GenerativeModel
import com.indianathe3rd.identity.domain.model.AppClassification
import com.indianathe3rd.identity.domain.repository.ClassificationRepository
import com.indianathe3rd.identity.domain.usecase.usage.GetAppNameUsecase
import com.indianathe3rd.identity.domain.model.AppCategory

class ClassificationRepositoryImpl(
    private val getAppNameUsecase: GetAppNameUsecase
) : ClassificationRepository {
    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash", // Use a valid model name like "gemini-1.5-flash" or "gemini-2.0-flash"
        apiKey = "AQ.Ab8RN6LQ0p74R9LcfJHt1sadOOBVEzE4yvzVP5ue0K2_ZaCWMg" // Ensure this is a valid Google AI Studio API key starting with "AIzaSy..."
    )

    override suspend fun getClassifications(packageNames: List<String>): List<AppClassification> {
        TODO("Not yet implemented")
    }

    override suspend fun saveClassification(classification: AppClassification) {
        TODO("Not yet implemented")
    }

    override suspend fun createClassification(packageName: String): AppClassification {
    return try {


        val prompt = """
            Classify the following application into exactly ONE of these categories:
            
            - SOCIAL
            - ENTERTAINMENT
            - GAMING
            - PRODUCTIVITY
            - READING
            - COMMUNICATION
            - UTILITY
            - OTHER
            
            Package name: $packageName
            
            Return ONLY the category name in uppercase. Do not provide an explanation, reasoning, punctuation, or any other text.
        """.trimIndent()

        val response = generativeModel.generateContent(prompt = prompt)

        val categoryText = response.text
            ?.trim()
            ?.uppercase()

        val category = try {
            AppCategory.valueOf(categoryText ?: "OTHER")
        } catch (e: Exception) {
            AppCategory.OTHER
        }
         AppClassification(
            packageName = packageName,
            appName = getAppNameUsecase(packageName),
            category = category
        )} catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "Error creating classification for package: $packageName", e)
        AppClassification(
            packageName = packageName,
            appName = getAppNameUsecase(packageName),
            category = AppCategory.OTHER
        )
        }
    }


}


private const val TAG = "ClassificationRepository"
