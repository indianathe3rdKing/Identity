package com.indianathe3rd.identity.domain.model

import kotlinx.serialization.Serializable

sealed interface Result {
    data object Success : Result
    data class Error(val message: String?) : Result
}

@Serializable
data class Profile(
    val id: String,
    val email: String,
    val username: String? = null,
    val avatar_url: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null
)

@Serializable
data class SupabaseUser(
    val id: String,
    val email: String? = null,
    val phone: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null
)

@Serializable
data class SignUpRequest(
    val email: String,
    val password: String
)

@Serializable
data class SignInRequest(
    val email: String,
    val password: String
)