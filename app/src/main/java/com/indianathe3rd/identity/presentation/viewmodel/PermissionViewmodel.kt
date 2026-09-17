package com.indianathe3rd.identity.presentation.viewmodel

import android.Manifest
import android.app.Activity
import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.getSystemService
import android.os.Process
import android.provider.Settings
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class PermissionViewmodel @Inject constructor(
    @ApplicationContext private val context: Context
): ViewModel() {


        fun isUsageAccessGranted(): Boolean{
            val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
            val mode = appOps.noteOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS
                , Process.myUid(),
                context.packageName
            )

            return mode == AppOpsManager.MODE_ALLOWED
        }



    fun requestUsageAccessPermission(){

            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName,null)
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)

    }


}