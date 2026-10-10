package com.indianathe3rd.identity.data.repository.supabase

import com.indianathe3rd.identity.domain.model.Result
import com.indianathe3rd.identity.domain.model.SignInRequest
import com.indianathe3rd.identity.domain.model.SignUpRequest
import com.indianathe3rd.identity.domain.repository.supabase.AuthRepository
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.security.MessageDigest
import java.util.UUID
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: Auth
): AuthRepository {
    override suspend fun signUp(signUp: SignUpRequest): Flow<Result> = flow {
        try {
           auth.signUpWith(Email){
               email=signUp.email
               password=signUp.password
           }
        }catch (e: Exception){
            emit(Result.Error(e.message))
        }
    }

    override suspend fun signIn(signIn: SignInRequest): Flow<Result> = flow {
        try {
            auth.signInWith(Email){
                email = signIn.email
                password = signIn.password
            }
        }catch (e: Exception){
            emit(Result.Error(e.localizedMessage))
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