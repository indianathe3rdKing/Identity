package com.indianathe3rd.identity.presentation.viewmodel

import android.Manifest
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

class PermissionViewmodel {


        fun isUsageAccessGranted(context: Context): Boolean{
            val appOps = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
            val mode = appOps.noteOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS
                , Process.myUid(),
                context.packageName
            )

            return mode == AppOpsManager.MODE_ALLOWED
        }



    fun requestUsageAccessPermission(context: Context){
        if (!isUsageAccessGranted(context)){
            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName,null)
            }

            context.startActivity(intent)
        }
    }
}