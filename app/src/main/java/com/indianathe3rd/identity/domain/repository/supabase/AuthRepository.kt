package com.indianathe3rd.identity.domain.repository.supabase

import com.indianathe3rd.identity.domain.model.Result
import com.indianathe3rd.identity.domain.model.SignInRequest
import com.indianathe3rd.identity.domain.model.SignUpRequest
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signUp(signUp: SignUpRequest): Flow<Result>
    suspend fun signIn(signIn: SignInRequest): Flow<Result>
    suspend fun createNonce(): String
    suspend fun logiWithGoogle()
}