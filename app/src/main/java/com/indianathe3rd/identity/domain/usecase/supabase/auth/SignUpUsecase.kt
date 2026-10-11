package com.indianathe3rd.identity.domain.usecase.supabase.auth

import com.indianathe3rd.identity.domain.model.Result
import com.indianathe3rd.identity.domain.model.SignUpRequest
import com.indianathe3rd.identity.domain.repository.supabase.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpUsecase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(signUp: SignUpRequest): Flow<Result> = repository.signUp(signUp)
}