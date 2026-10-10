package com.indianathe3rd.identity.domain.repository.supabase

import com.indianathe3rd.identity.domain.model.Profile

interface ProfileRepository {
    suspend fun createProfile(profile: Profile): Boolean
    suspend fun getProfile(): Profile?
    suspend fun updateProfile(profile: Profile)
    suspend fun deleteProfile(id: String)

}