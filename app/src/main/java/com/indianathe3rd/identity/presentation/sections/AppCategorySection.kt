package com.indianathe3rd.identity.presentation.sections

import androidx.annotation.ContentView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.indianathe3rd.identity.presentation.components.AppCatHeader
import com.indianathe3rd.identity.presentation.components.AppCategoryItem
import com.indianathe3rd.identity.presentation.ui.theme.SurfaceLight
import com.indianathe3rd.identity.presentation.viewmodel.AppUsageViewModel

@Composable
fun AppCategorySection(viewModel: AppUsageViewModel = hiltViewModel()){

    val categoryUsageList by viewModel.categoryUsage
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.elevatedCardColors(
            containerColor = SurfaceLight
        )
    ) {
        Column(

        ) {
            AppCatHeader()
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
               items(categoryUsageList){
                   cat ->
                   AppCategoryItem(category = cat)
               }
            }
        }
    }
}