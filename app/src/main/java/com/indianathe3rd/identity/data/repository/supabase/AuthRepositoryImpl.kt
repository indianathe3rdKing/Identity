package com.indianathe3rd.identity.data.repository.supabase

import com.indianathe3rd.identity.data.remote.SupabaseClient
import com.indianathe3rd.identity.domain.model.AuthResponse
import com.indianathe3rd.identity.domain.model.SignInRequest
import com.indianathe3rd.identity.domain.model.SignUpRequest
import com.indianathe3rd.identity.domain.repository.supabase.AuthRepository
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.security.MessageDigest
import java.util.UUID

class AuthRepositoryImpl(): AuthRepository {
    override suspend fun signUp(signUp: SignUpRequest): Flow<AuthResponse> = flow {
        try {
            SupabaseClient.supabase.auth.signUpWith(Email){
                email = signUp.email
                password = signUp.password
            }
        }catch (e: Exception){
            emit(AuthResponse.Error(e.message))
        }
    }

    override suspend fun signIn(signIn: SignInRequest): Flow<AuthResponse> = flow {
        try {
            SupabaseClient.supabase.auth.signInWith(Email){
                email = signIn.email
                password = signIn.password
            }
        }catch (e: Exception){
            emit(AuthResponse.Error(e.localizedMessage))
        }
    }

    override suspend fun createNonce(): String {
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold(""){str, it -> str+ "%02x".format(it)}
    }

    override suspend fun logiWithGoogle() {
        TODO("Not yet implemented")
    }
}