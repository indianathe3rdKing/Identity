package com.indianathe3rd.identity.presentation.sections

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.indianathe3rd.identity.presentation.components.AppUsageItem
import com.indianathe3rd.identity.presentation.viewmodel.AppUsageViewModel

@Composable
fun AppUsageSection(
    viewModel: AppUsageViewModel = viewModel()
) {
    val appUsageList by viewModel.appUsage
    val context = LocalContext.current
    // Load the data when the screen appears
    LaunchedEffect(Unit) {
        viewModel.getAppUsageStats( )
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(appUsageList) { app ->
            AppUsageItem(app)
        }
    }
}