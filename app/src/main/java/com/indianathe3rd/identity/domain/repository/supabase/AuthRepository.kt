package com.indianathe3rd.identity.domain.repository.supabase

import com.indianathe3rd.identity.domain.model.Result
import com.indianathe3rd.identity.domain.model.SignInRequest
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.StateFlow

interface AuthRepository {
    val sessionStatus: StateFlow<SessionStatus>
    suspend fun signUp(signUp: SignInRequest): Result
    suspend fun signIn(signIn: SignInRequest): Result
    suspend fun createNonce(): String
    suspend fun logiWithGoogle(): Result

    suspend fun sendPasswordReset(email: String): Result
    suspend fun updatePassword(newPassword: String): Result
}