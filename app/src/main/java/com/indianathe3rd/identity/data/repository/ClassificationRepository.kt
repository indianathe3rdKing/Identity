package com.indianathe3rd.identity.data.repository

import android.util.Log
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.indianathe3rd.identity.data.Config
import com.indianathe3rd.identity.domain.model.AppClassification
import com.indianathe3rd.identity.domain.repository.ClassificationRepository
import com.indianathe3rd.identity.domain.usecase.usage.GetAppNameUsecase
import com.indianathe3rd.identity.domain.model.AppCategory

class ClassificationRepositoryImpl(
    private val getAppNameUsecase: GetAppNameUsecase
) : ClassificationRepository {
   private val openAI = OpenAI(token = Config.OPENAI_API_KEY)

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

        val response = openAI.chatCompletion(
            ChatCompletionRequest(
                model = ModelId("gpt-5.4-nano"),
                messages = listOf(
                    ChatMessage(role = ChatRole.User, content = prompt)
                )
            )
        )

        val categoryText = response
            .choices
            .firstOrNull()
            ?.message
            ?.content
            ?.trim()
            ?.uppercase()

        val category = try {
            AppCategory.valueOf(categoryText ?: "OTHER")
        } catch (e: Exception) {
            Log.e(TAG, "Error parsing category for package: $packageName, received: $categoryText", e)
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
