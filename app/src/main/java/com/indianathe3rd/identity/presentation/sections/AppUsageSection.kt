package com.indianathe3rd.identity.presentation.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.indianathe3rd.identity.R
import com.indianathe3rd.identity.presentation.components.AppUsageItem
import com.indianathe3rd.identity.presentation.ui.theme.SoftPurple
import com.indianathe3rd.identity.presentation.ui.theme.SoftTextTertiary
import com.indianathe3rd.identity.presentation.ui.theme.TextPrimary
import com.indianathe3rd.identity.presentation.viewmodel.AppUsageViewModel

@Composable
fun AppUsageSection(
    viewModel: AppUsageViewModel = hiltViewModel()
) {
    val appUsageList by viewModel.appUsage

    val showAll by remember {mutableStateOf(false)}
    val displayedApps = if (showAll) appUsageList else appUsageList.take(4)
    // Load the data when the screen appears
    // (Loaded in HomeScreen)

    Column() {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.height(20.dp)
            ) {
                Column {
                    Icon(
                        painter = painterResource(R.drawable.bar_chart),
                        contentDescription = "bar chart icon",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                }
                Text(
                    "Top Apps",
                    style = MaterialTheme.typography.titleSmall,
                    color = TextPrimary

                )
            }

            Row(
                modifier = Modifier
                    .width(60.dp)
                    .height(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "View All",
                    style = MaterialTheme.typography.bodySmall,
                    color = SoftTextTertiary
                )

                Icon(
                    Icons.Outlined.ArrowForwardIos,
                    contentDescription = "Arrow Forward",
                    tint = SoftTextTertiary,
                    modifier = Modifier
                        .size(20.dp)

                )


            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            items(displayedApps) { app ->
                Spacer(Modifier.width(4.dp))
                AppUsageItem(app)
                Spacer(Modifier.width(2.dp))
            }
        }
    }
}