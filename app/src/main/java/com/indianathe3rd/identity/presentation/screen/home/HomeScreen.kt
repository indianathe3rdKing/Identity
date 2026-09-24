package com.indianathe3rd.identity.presentation.screen.home

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.indianathe3rd.identity.presentation.components.PermissionsAlertDialog
import com.indianathe3rd.identity.presentation.screen.tutorial.TutorialScreen
import com.indianathe3rd.identity.presentation.sections.AppUsageSection
import com.indianathe3rd.identity.presentation.viewmodel.PermissionViewmodel

@Composable
fun HomeScreen(permissionViewmodel: PermissionViewmodel = viewModel()) {

    val isPermissionGranted = permissionViewmodel.isUsageAccessGranted()
    val context = LocalContext.current
    if (!isPermissionGranted){
    PermissionsAlertDialog(
        onAccept = {permissionViewmodel.requestUsageAccessPermission()},
        onDismiss =    {(context as? Activity)?.finish()} ,
        onTutorial = { TutorialScreen()}
    )}
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp,8.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to the Home Screen!",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        // Add more UI elements for the home screen here
        AppUsageSection()
    }

}