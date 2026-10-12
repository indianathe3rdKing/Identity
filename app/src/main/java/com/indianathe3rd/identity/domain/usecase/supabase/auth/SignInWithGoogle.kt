package com.indianathe3rd.identity.domain.usecase.supabase.auth

import com.indianathe3rd.identity.domain.model.Result
import com.indianathe3rd.identity.domain.repository.supabase.AuthRepository
import com.indianathe3rd.identity.domain.repository.supabase.ProfileRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignInWithGoogle @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): Result = repository.logiWithGoogle()

}