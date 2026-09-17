package com.indianathe3rd.identity.presentation.sections

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.indianathe3rd.identity.presentation.viewmodel.AppUsageViewModel

@Composable
fun AppUsageScreen(
    viewModel: AppUsageViewModel = viewModel()
) {
    val appUsageList by viewModel.appUsage
    val context = LocalContext.current
    // Load the data when the screen appears
    LaunchedEffect(Unit) {
        viewModel.getAppUsageStats(context)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(appUsageList) { app ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = app.appName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Time: ${app.totalTimeInForeground / 1000} sec",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = app.packageName,
                    style = MaterialTheme.typography.bodySmall
                )
                Divider(modifier = Modifier.padding(top = 8.dp))
            }
        }
    }
}