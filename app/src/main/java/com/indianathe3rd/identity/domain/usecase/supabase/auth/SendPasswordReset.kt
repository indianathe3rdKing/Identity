package com.indianathe3rd.identity.domain.usecase.supabase.auth

import com.indianathe3rd.identity.domain.model.Result
import com.indianathe3rd.identity.domain.repository.supabase.AuthRepository
import javax.inject.Inject

class SendPasswordReset @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String): Result = repository.sendPasswordReset(email)
}