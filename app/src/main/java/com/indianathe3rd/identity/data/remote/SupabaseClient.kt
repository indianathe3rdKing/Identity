package com.indianathe3rd.identity.data.remote

import com.indianathe3rd.identity.data.Config
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    val supabase = createSupabaseClient(
        supabaseUrl = Config.SUPABASE_URL,
        supabaseKey = Config.SUPABASE_KEY
    ){
        install(Auth)
        install(Postgrest)
    }
}