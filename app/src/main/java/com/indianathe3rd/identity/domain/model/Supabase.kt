package com.indianathe3rd.identity.domain.model

import kotlinx.serialization.Serializable

sealed interface AuthResponse {
    data object Success : AuthResponse
    data class Error(val message: String?) : AuthResponse
}

@Serializable
data class Profile(
    val id: String,
    val email: String,
    val username: String? = null,
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