package com.indianathe3rd.identity.data.repository.supabase

import com.indianathe3rd.identity.domain.model.Result
import com.indianathe3rd.identity.domain.model.SignInRequest
import com.indianathe3rd.identity.domain.repository.supabase.AuthRepository
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.StateFlow
import java.security.MessageDigest
import java.util.UUID
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: Auth
) : AuthRepository {
    override val sessionStatus: StateFlow<SessionStatus> = auth.sessionStatus
    override suspend fun signUp(signUp: SignInRequest): Result {
        return try {
            auth.signUpWith(Email) {
                email = signUp.email
                password = signUp.password
            }
            Result.Success
        } catch (e: Exception) {
            Result.Error(e.localizedMessage ?: "Unknown Error")
        }
    }

    override suspend fun signIn(signIn: SignInRequest): Result {
        return try {
            auth.signInWith(Email) {
                email = signIn.email
                password = signIn.password
            }
            Result.Success
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }

    override suspend fun createNonce(): String {
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    override suspend fun logiWithGoogle(): Result {
        return try {
            auth.signInWith(Google)
            Result.Success
        } catch (e: Exception) {
            Result.Error(e.localizedMessage ?: "Unknown Error")
        }
    }
}