package com.indianathe3rd.identity.data.repository.supabase

import android.util.Log
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
            val session = auth.currentSessionOrNull()
            val user = auth.currentUserOrNull()

            if (user != null && user.identities.isNullOrEmpty()) {
                Result.Error("This email is already registered. Please sign in.")
            } else if (session == null) {
                Result.Error("This email is already registered. Please sign in.")
            } else {
                Result.Success
            }
        } catch (e: Exception) {
            Log.e(TAG, "SignUp Error : ${e.message}", e)

            val message = when {
                e.message?.contains("already", ignoreCase = true) == true ||
                        e.message?.contains("registered", ignoreCase = true) == true ||
                        e.message?.contains("exists", ignoreCase = true) == true ->
                    "This email is already registered. Please sign in."
                e.message?.contains("Anonymous sign-ins are disabled", ignoreCase = true)== true->
                    "Please enter your Email and Password"
                e.message?.contains("password", ignoreCase = true) == true ->
                    "Password is too weak."

                else -> e.message ?: "Sign up failed"
            }
            Result.Error(message)
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
            Log.e(TAG, "SignIn Error : ${e.localizedMessage}", e)
            val message = when {
                e.message?.contains("Invalid login credentials", ignoreCase = true) == true ||
                        e.message?.contains("invalid", ignoreCase = true) == true ->
                    "Incorrect email or password"

                e.message?.contains("Email not confirmed", ignoreCase = true) == true ->
                    "Please confirm your email first"
                e.message?.contains("missing email or phone", ignoreCase = true)== true ->
                    "Please enter your Email and Password"
                else -> e.message ?: e.localizedMessage ?: "Something is wrong"
            }
            Result.Error(message)
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
            Result.Idle
        } catch (e: Exception) {
            Log.e(TAG, "Error : $e")

            Result.Error(e.localizedMessage ?: "Unknown Error")
        }
    }

    override suspend fun sendPasswordReset(email: String): Result {
       return try {
            auth.resetPasswordForEmail(
                email = email
            )
           Result.Success
        }catch (e: Exception){
            Result.Error(e.localizedMessage ?: "Failed to send Reset email")
        }
    }

    override suspend fun updatePassword(newPassword: String): Result {
        return try {
            auth.updateUser {
                password = newPassword
            }
            Result.Success
        }catch (e: Exception){
            Result.Error(e.localizedMessage ?: "Failed to update password")
        }
    }


    companion object {
        private val TAG = "AuthImpl"
    }
}