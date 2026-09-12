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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cafe.adriel.voyager.navigator.Navigator
import com.indianathe3rd.identity.presentation.components.PermissionsAlertDialog
import com.indianathe3rd.identity.presentation.viewmodel.PermissionViewmodel

@Composable
fun HomeScreen() {
    val viewModel = PermissionViewmodel()
    if(!viewModel.isUsageAccessGranted()){
    PermissionsAlertDialog(
        onAccept = {viewModel.requestUsageAccessPermission()}
        , onDismiss = {(context as? Activity
        )?.finish() },{ Navigator.push(TutorialScreen()) }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to the Home Screen!",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        // Add more UI elements for the home screen here
    }

}