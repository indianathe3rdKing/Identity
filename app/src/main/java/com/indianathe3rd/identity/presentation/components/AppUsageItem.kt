package com.indianathe3rd.identity.presentation.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.indianathe3rd.identity.data.mapper.AppUsageMapper.formatUsageTime
import com.indianathe3rd.identity.domain.model.AppUsage

@Composable
fun AppUsageItem(app: AppUsage) {
    val context = LocalContext.current
    val icon = remember(app.packageName) {
        try {
            context.packageManager.getApplicationIcon(app.packageName)
        } catch (e: Exception) {
            null
        }
    }
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
            , verticalAlignment = Alignment.CenterVertically
        ) {

            icon?.let { icon ->
                Image(
                    bitmap = (icon.toBitmap().asImageBitmap()),
                    contentDescription = app.appName,
                    modifier = Modifier.size(40.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {

                Text(
                    text = app.appName,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = app.totalTimeInForeground.formatUsageTime(),
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

    }
}

