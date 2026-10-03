package com.indianathe3rd.identity.presentation.components

import android.widget.ProgressBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fitOutside
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.indianathe3rd.identity.R
import com.indianathe3rd.identity.domain.model.CategoryUsage
import com.indianathe3rd.identity.presentation.ui.theme.EntertainmentOrange
import com.indianathe3rd.identity.presentation.ui.theme.TextSecondary

@Composable
fun AppCategoryItem(category: CategoryUsage) {

    Column(
        modifier = Modifier
            .padding(4.dp)
            .width(120.dp)
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(EntertainmentOrange)
                .clip(RoundedCornerShape(25.dp)),
        ) {
            Image(
                painter = painterResource(R.drawable.quill_icon_game),
                contentDescription = "Category Icon",
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(25.dp)),
                contentScale = ContentScale.Fit,

                )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = category.category.name,
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${category.percentage}%",
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = category.percentage / 100f,
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(50)),
            color = EntertainmentOrange,
            trackColor = TextSecondary.copy(0.9f)
        )


    }

}