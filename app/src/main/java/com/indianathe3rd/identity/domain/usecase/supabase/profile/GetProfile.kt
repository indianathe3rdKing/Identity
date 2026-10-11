package com.indianathe3rd.identity.domain.usecase.supabase.profile

import com.indianathe3rd.identity.domain.model.Profile
import com.indianathe3rd.identity.domain.repository.supabase.ProfileRepository
import javax.inject.Inject

class GetProfile @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(id: String): Profile? = repository.getProfile(id)
}