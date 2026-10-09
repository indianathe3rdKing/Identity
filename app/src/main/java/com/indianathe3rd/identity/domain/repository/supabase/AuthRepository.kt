package com.indianathe3rd.identity.domain.repository.supabase

import com.indianathe3rd.identity.domain.model.AuthResponse
import com.indianathe3rd.identity.domain.model.SignInRequest
import com.indianathe3rd.identity.domain.model.SignUpRequest
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signUp(signUp: SignUpRequest): Flow<AuthResponse>
    suspend fun signIn(signIn: SignInRequest): Flow<AuthResponse>
    suspend fun createNonce(): String
    suspend fun logiWithGoogle()
}