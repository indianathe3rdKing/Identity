package com.indianathe3rd.identity.data.repository.supabase

import com.indianathe3rd.identity.domain.model.Profile
import com.indianathe3rd.identity.domain.model.Result
import com.indianathe3rd.identity.domain.repository.supabase.ProfileRepository
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest,
    private val storage: Storage
) : ProfileRepository {
    override suspend fun createProfile(profile: Profile): Result {
        return try {
            withContext(Dispatchers.IO) {
                val profile = Profile(
                    id = profile.id,
                    username = profile.username,
                    email = profile.email,
                    avatar_url = profile.avatar_url,
                )
            }
            postgrest.from("profiles").insert(profile)
            Result.Success
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e.localizedMessage)
        }
    }


    override suspend fun getProfile(id: String): Profile? {
        return try {

            withContext(Dispatchers.IO) {
                postgrest.from("profiles").select {
                    filter {
                        eq("id", id)
                    }
                }.decodeSingleOrNull()
            }
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
            null
        }
    }


    override suspend fun updateProfile(profile: Profile) {
        try {


            withContext(Dispatchers.IO) {
                postgrest.from("profile").update({
                    set("username", profile.username)
                    set("email", profile.email)
                    set("avatar", profile.avatar_url)
                })
            }
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }

    override suspend fun deleteProfile(id: String) {
        return withContext(Dispatchers.IO) {
            postgrest.from("profiles").delete {
                filter {
                    eq("id", id)
                }
            }
        }
    }
}