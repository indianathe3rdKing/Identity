package com.indianathe3rd.identity.domain.usecase.supabase.auth

import com.indianathe3rd.identity.domain.model.Result
import com.indianathe3rd.identity.domain.model.SignInRequest
import com.indianathe3rd.identity.domain.repository.supabase.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInUsecase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(signIn: SignInRequest): Result = repository.signIn(signIn)
}