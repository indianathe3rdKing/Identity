package com.indianathe3rd.identity

import android.app.Application
import com.clerk.api.Clerk
import com.indianathe3rd.identity.data.remote.Config

class IdentityApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Clerk.initialize(this,Config.CLERK_API_KEY)
    }
}