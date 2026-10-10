package com.indianathe3rd.identity.domain.repository.supabase

import com.indianathe3rd.identity.domain.model.Profile
import com.indianathe3rd.identity.domain.model.Result

interface ProfileRepository {
    suspend fun createProfile(profile: Profile): Result
    suspend fun getProfile(id: String): Profile?
    suspend fun updateProfile(profile: Profile)
    suspend fun deleteProfile(id: String)

}